package www.hmn.az.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.hmn.az.dto.request.MarkRequest;
import www.hmn.az.dto.response.RespMark;
import www.hmn.az.dto.response.Response;
import www.hmn.az.service.MarkService;

import java.util.List;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
    @Autowired
    public MarkService service;

    @PostMapping("/add")
    public Response addMark(@RequestBody MarkRequest request) {

        return service.addMark(request);
    }


    @GetMapping("/list")
    public Response<List<RespMark>> getListMark(){

        return service.getListMark();
    }

//    @GetMapping("/list")
//    public Response<List<RespMark>> getListMark(){
//
//        return service.getListMark();
//    }
}
