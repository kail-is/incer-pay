package incerpay.payment.presentation;

import incerpay.payment.common.dto.*;
import incerpay.payment.common.lib.response.Response;
import incerpay.payment.domain.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/request")
    public Response request(@RequestBody PaymentRequestCommand command) {
        PaymentView view = service.request(command);
        return Response.ok(view);
    }

    @PostMapping("/approve")
    public Response approve(@RequestBody PaymentApproveCommand command) {
        PaymentView view = service.approve(command);
        return Response.ok(view);
    }

    @PostMapping("/cancel")
    public Response cancel(@RequestBody PaymentCancelCommand command) {
        PaymentView view = service.cancel(command);
        return Response.ok(view);
    }

    @PostMapping("/reject")
    public Response reject(@RequestBody PaymentRejectCommand command) {
        PaymentView view = service.reject(command);
        return Response.ok(view);
    }

    @GetMapping("/seller/{sellerId}/list")
    public Response readBySellerId(@PathVariable String sellerId) {
        PaymentListView view = service.readBySellerId(sellerId);
        return Response.ok(view);
    }

    @GetMapping("/seller/{sellerId}/detail/{paymentId}")
    public Response readDetailBySellerId(@PathVariable String sellerId, @PathVariable String paymentId) {
        PaymentDetailView view = service.readDetailBySellerId(sellerId, paymentId);
        return Response.ok(view);
    }
}
