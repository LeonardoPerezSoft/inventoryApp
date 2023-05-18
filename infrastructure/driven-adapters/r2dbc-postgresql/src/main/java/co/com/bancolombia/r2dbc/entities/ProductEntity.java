package co.com.bancolombia.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    private Integer id;
    private String name;
    @Column("ininventory")
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;
}
