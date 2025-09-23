package www.hmn.az.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.hmn.az.dto.request.CarRequest;
import www.hmn.az.dto.response.Response;
import www.hmn.az.entity.Car;
import www.hmn.az.service.CarService;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response addCar(
            @RequestPart("carInfo") String jsonCar,
            @RequestPart("image") MultipartFile file) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        CarRequest car = mapper.readValue(jsonCar, CarRequest.class);

        return service.addCar(car,file);
    }

   @GetMapping("/listCar")
    public Response<Page<Car>> getAllCars(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "4") int size){
       PageRequest pageable = PageRequest.of(page, size);

       return service.getAllCars(pageable);

   }







}
