package co.com.bancolombia.model.productQuantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductQuantity {
    private Integer idProduct;
    private int quantity;
}
