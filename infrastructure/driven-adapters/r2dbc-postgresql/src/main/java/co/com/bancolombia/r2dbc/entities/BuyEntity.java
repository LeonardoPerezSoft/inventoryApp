package co.com.bancolombia.r2dbc.entities;

import java.time.LocalDateTime;

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
@Table(name = "buy")
public class BuyEntity {
    @Id
    private Integer id;
    private String dni;
    private LocalDateTime date;
    @Column("idtype")
    private String idType;
    @Column("clientname")
    private String clientName;
}
