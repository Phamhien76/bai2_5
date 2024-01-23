package ra.bai2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPatchRequest {
    private long id;
    private boolean status;
}
