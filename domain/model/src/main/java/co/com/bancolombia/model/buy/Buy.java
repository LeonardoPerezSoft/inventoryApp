package co.com.bancolombia.model.buy;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Buy {
    private Integer id;
    private String dni;
    private LocalDateTime date;
    private String idType;
    private String clientName;
}
