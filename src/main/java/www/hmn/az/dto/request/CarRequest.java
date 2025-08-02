package www.hmn.az.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import www.hmn.az.entity.Mark;

@Data
public class CarRequest {

    private String name;
    private String registerNumber;
    private String gasType;
    private String transmission;
    private Long markId;
}
