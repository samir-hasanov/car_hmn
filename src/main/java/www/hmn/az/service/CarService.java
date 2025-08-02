package www.hmn.az.service;

import org.springframework.web.multipart.MultipartFile;
import www.hmn.az.dto.request.CarRequest;
import www.hmn.az.dto.response.Response;

public interface CarService {
    Response addCar(CarRequest car, MultipartFile file);
}
