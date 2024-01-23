package ra.bai2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class CategoriesPatchRequest {
    private long id;
    private boolean status;
}
