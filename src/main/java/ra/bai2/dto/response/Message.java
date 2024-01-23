package ra.bai2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    private String message;
    private String error;
    public Message (String message){
        this.message = message;
    }
}
