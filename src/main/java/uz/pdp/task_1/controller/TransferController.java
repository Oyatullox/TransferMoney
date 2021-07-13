package uz.pdp.task_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task_1.payload.ApiResponse;
import uz.pdp.task_1.payload.TransferDto;
import uz.pdp.task_1.service.TransferService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/trarsfer")
public class TransferController {

    @Autowired
    TransferService service;

    @PostMapping
    public HttpEntity<?> transferMoney(HttpServletRequest request, @RequestBody TransferDto transferDto){
        ApiResponse apiResponse=service.transferMoney(request,transferDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
