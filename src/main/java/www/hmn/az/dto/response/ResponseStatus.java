package www.hmn.az.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {

    private Integer code;
    private String msj;
    private static Integer SUCCESS_CODE = 1;
    private static String MESSAGE_CODE = "SUCCESS";

    public static ResponseStatus getMessage() {

        return new ResponseStatus(SUCCESS_CODE, MESSAGE_CODE);
    }
}
