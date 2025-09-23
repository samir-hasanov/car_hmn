package www.hmn.az.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import www.hmn.az.dto.request.CarRequest;
import www.hmn.az.dto.response.Response;
import www.hmn.az.dto.response.ResponseStatus;
import www.hmn.az.entity.Car;
import www.hmn.az.entity.Mark;
import www.hmn.az.exception.ExceptionConstants;
import www.hmn.az.exception.MyException;
import www.hmn.az.repository.CarRepository;
import www.hmn.az.repository.MarkRepository;
import www.hmn.az.service.CarService;
import www.hmn.az.service.FileService;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private MarkRepository markaRepository;

    private static final String bucket = "car-hmn";

    @Override
    public Response addCar(CarRequest carRequest, MultipartFile file) {

        Response response = new Response();

        try {

            if (carRequest == null && file == null) {
                throw new MyException(ExceptionConstants.REQUEST_NULL, "Request fail");
            }

            String imageUrl = fileService.uploadToMinio(file, bucket);

            Car car = new Car();
            car.setName(carRequest.getName());
            car.setRegisterNumber(carRequest.getRegisterNumber());
            car.setGasType(carRequest.getGasType());
            car.setTransmission(carRequest.getTransmission());
            car.setImageUrl(imageUrl);
            Mark mark = markaRepository.findByDeletedFalseAndId(carRequest.getMarkId());
            car.setMark(mark);
            carRepository.save(car);
            response.setStatus(ResponseStatus.getMessage());

        } catch (MyException e) {
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<Page<Car>> getAllCars(PageRequest pageable) {
        Response response = new Response();

        try {
            Page car = carRepository.findAll(pageable);
            if (car == null) {
                throw new MyException(ExceptionConstants.NOT_FOUND, "car not found");
            }

            response.setT(car);
            response.setStatus(ResponseStatus.getMessage());

        } catch (MyException e) {
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
