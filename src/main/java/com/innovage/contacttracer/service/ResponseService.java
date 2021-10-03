package com.innovage.contacttracer.service;

import com.innovage.contacttracer.utils.ResponseModel;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseModel successResponse(Object e) {
        ResponseModel rm = new ResponseModel();
        rm.setResponse(e);
        rm.setSuccess(true);
        rm.setMessage(null);

        return rm;
    }

    public ResponseModel failResponse(String message) {
        ResponseModel rm = new ResponseModel();
        rm.setResponse(null);
        rm.setSuccess(false);
        rm.setMessage(message);

        return rm;
    }


}
