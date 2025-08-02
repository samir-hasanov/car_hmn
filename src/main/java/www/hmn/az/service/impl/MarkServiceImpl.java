package www.hmn.az.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.hmn.az.dto.request.MarkRequest;
import www.hmn.az.dto.response.RespCar;
import www.hmn.az.dto.response.RespMark;
import www.hmn.az.dto.response.Response;
import www.hmn.az.dto.response.ResponseStatus;
import www.hmn.az.entity.Car;
import www.hmn.az.entity.Mark;
import www.hmn.az.exception.ExceptionConstants;
import www.hmn.az.exception.MyException;
import www.hmn.az.repository.MarkRepository;
import www.hmn.az.service.MarkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MarkServiceImpl implements MarkService {
    @Autowired
    public MarkRepository repository;

    @Override
    public Response addMark(MarkRequest request) {
        Response response = new Response();
        try {
            if (request.getName() == null) {
                throw new MyException(ExceptionConstants.REQUEST_NULL, "Request null");
            }
            Mark mark = new Mark();
            mark.setName(request.getName());
            repository.save(mark);
            response.setStatus(ResponseStatus.getMessage());
        } catch (MyException e) {
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<RespMark>> getListMark() {
        Response response = new Response();

        try {
            List<Mark> markList = repository.findByDeletedFalse()
                    .filter(list -> !list.isEmpty())
                    .orElseThrow(() -> new MyException(ExceptionConstants.NOT_FOUND, "Mark not found !!!"));

            response.setT(convertMarkListToRespMarkList.apply(markList));
            response.setStatus(ResponseStatus.getMessage());

        } catch (MyException e) {
            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return response;
    }

//    @Override
//    public Response<List<RespMark>> getListMark() {
//
//        Response response = new Response();
//        try {
//
//            List<Mark> markList = repository.findByDeletedFalse();
//
//            if (markList == null) {
//                throw new MyException(ExceptionConstants.NOT_FOUND, "Not found ");
//            }
//
//            response.setT(convertMarkListToRespMarkList.apply( markList));
//            response.setStatus(ResponseStatus.getMessage());
//
//
//        } catch (MyException e) {
//            response.setStatus(new ResponseStatus(e.getCode(), e.getMessage()));
//        }
//
//        return response;
//
//    }


    // Car -> RespCar çevirici
    private final Function<Car, RespCar> convertCarToRespCar = car ->
            RespCar.builder()
                    .id(car.getId())
                    .name(car.getName())
                    .registerNumber(car.getRegisterNumber())
                    .gasType(car.getGasType())
                    .transmission(car.getTransmission())
                    .imageUrl(car.getImageUrl())
                    .build();

    // List<Car> -> List<RespCar>
    private final Function<List<Car>, List<RespCar>> convertCarListToRespCarList = cars ->
            cars == null ? new ArrayList<>() : cars.stream().map(convertCarToRespCar).collect(Collectors.toList());

    // Mark -> RespMark
    private final Function<Mark, RespMark> convertMarkToRespMark = mark ->
            RespMark.builder()
                    .id(mark.getId())
                    .name(mark.getName())
                    .cars(convertCarListToRespCarList.apply(mark.getCars()))
                    .build();

    //  List<Mark> -> List<RespMark>
    private final Function<List<Mark>, List<RespMark>> convertMarkListToRespMarkList = marks ->
            marks.stream().map(convertMarkToRespMark).collect(Collectors.toList());
}


