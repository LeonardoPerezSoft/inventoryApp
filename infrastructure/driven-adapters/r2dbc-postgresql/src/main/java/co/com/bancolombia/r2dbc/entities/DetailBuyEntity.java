package co.com.bancolombia.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detailbuy")
public class DetailBuyEntity {
    @Id
    private Integer id;
    private Integer product;
    private Integer buy;
    private Integer quantity;
}
