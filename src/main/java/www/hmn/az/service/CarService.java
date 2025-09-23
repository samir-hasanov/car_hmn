package www.hmn.az.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import www.hmn.az.dto.request.CarRequest;
import www.hmn.az.dto.response.Response;
import www.hmn.az.entity.Car;

public interface CarService {
    Response addCar(CarRequest car, MultipartFile file);

    Response<Page<Car>> getAllCars(PageRequest pageable);
}
