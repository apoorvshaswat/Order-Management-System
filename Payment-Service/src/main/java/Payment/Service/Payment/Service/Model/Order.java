package Payment.Service.Payment.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String userId;
    private List<String> productIds;
    private String status;
}
