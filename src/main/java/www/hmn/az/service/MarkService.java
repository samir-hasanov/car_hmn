package www.hmn.az.service;

import www.hmn.az.dto.request.MarkRequest;
import www.hmn.az.dto.response.RespMark;
import www.hmn.az.dto.response.Response;

import java.util.List;

public interface MarkService {
    Response addMark(MarkRequest request);

    Response<List<RespMark>> getListMark();

//    Response<List<RespMark>> getListMark();
}
