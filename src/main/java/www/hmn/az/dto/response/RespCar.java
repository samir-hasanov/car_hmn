package www.hmn.az.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import www.hmn.az.entity.Mark;

import java.util.Date;

@Data
@Builder
public class RespCar {

    private Long id;
    private String name;
    private String registerNumber;
    private String gasType;
    private String transmission;
    private String imageUrl;
    private Date createTime ;
    private Date updateTime;

}
