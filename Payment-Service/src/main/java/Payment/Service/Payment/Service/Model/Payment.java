package Payment.Service.Payment.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private String orderId;
    private String paymentStatus;
    private String userId;
    private List<String> productIds;

    public Payment(String orderId, String paymentStatus, String userId, List<String> productIds) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.userId = userId;
        this.productIds = productIds;
    }
}
