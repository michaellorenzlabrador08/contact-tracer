package com.innovage.contacttracer.resource;


import com.innovage.contacttracer.domain.Travels;
import com.innovage.contacttracer.dto.TravelsDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.service.ResponseService;
import com.innovage.contacttracer.service.TravelsService;
import com.innovage.contacttracer.utils.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travels")
public class TravelsResource {

    private final ResponseService rs;
    private final TravelsService ts;

    public TravelsResource(ResponseService rs, TravelsService ts) {
        this.rs = rs;
        this.ts = ts;
    }

    @PostMapping("/checkIn")
    private ResponseModel checkIn(TravelsDTO dto) {
        try {

            Travels travels = ts.checkIn(dto);
            return rs.successResponse(travels);
        } catch (CTRuntimeException e) {
            return rs.failResponse(e.getMessage());
        }
    }

    @PostMapping("/checkOut")
    private ResponseModel checkOut(TravelsDTO dto) {
        try {
            Travels latestTravels = ts.checkOut(dto);
            return rs.successResponse(latestTravels);
        } catch (CTRuntimeException e) {
            return rs.failResponse(e.getMessage());
        }
    }
}
