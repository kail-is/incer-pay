package incerpay.payment.domain.dto;

import java.util.UUID;

public record PaymentCancelCommand(
        UUID paymentId
) {
}
