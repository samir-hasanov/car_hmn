package www.hmn.az.dto.response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import www.hmn.az.entity.Car;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RespMark {
    private Long id;
    private String name;
    private List<RespCar> cars;
}
