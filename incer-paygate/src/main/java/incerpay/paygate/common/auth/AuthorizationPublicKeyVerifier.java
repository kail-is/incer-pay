package incerpay.paygate.common.auth;

import incerpay.paygate.common.exception.InvalidApiKeyException;
import incerpay.paygate.infrastructure.internal.IncerPaymentStoreCaller;
import incerpay.paygate.infrastructure.internal.dto.ApiKeyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationPublicKeyVerifier {

    // 로그 삭제 여부 확인 필요
    Logger log = LoggerFactory.getLogger(AuthorizationPublicKeyVerifier.class);

    private static final int KEY_LENGTH = 37;
    private static final String BEARER_PREFIX = "Bearer ";

    private final IncerPaymentStoreCaller incerPaymentStoreCaller;
    public AuthorizationPublicKeyVerifier(IncerPaymentStoreCaller incerPaymentStoreCaller) {
        this.incerPaymentStoreCaller = incerPaymentStoreCaller;
    }

    public boolean verify(Long sellerId, String apiKey, String apiKeyState) {
        ApiKeyInfo apiKeyInfo = new ApiKeyInfo(apiKey, apiKeyState);
        validateKey(apiKey);
        return incerPaymentStoreCaller.verifyPublicKey(sellerId, apiKey, apiKeyInfo.getApiKeyState());
    }

    private boolean validateKey(String apiKey) {

        if (apiKey == null || !apiKey.startsWith(BEARER_PREFIX)) {
            throw new InvalidApiKeyException();
        }

        String keyPart = apiKey.substring(BEARER_PREFIX.length());
        log.info("AuthorizationPublicKeyVerifier apiKey: {}, Parsed key length: {} ", apiKey, keyPart.length());

        if (keyPart.length() != KEY_LENGTH) {
            throw new InvalidApiKeyException();
        }

        return true;
    }

}
