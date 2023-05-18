package co.com.bancolombia.model.buyProduct;

import java.time.LocalDateTime;
import java.util.List;

import co.com.bancolombia.model.productQuantity.ProductQuantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BuyProduct {
    private Integer id;
    private String dni;
    private LocalDateTime date;
    private String idType;
    private String clientName;
    private List<ProductQuantity> products;
}
