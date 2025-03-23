package com.mivdhi.insurance.controller;

import com.mivdhi.insurance.data.dao.Insurance;
import com.mivdhi.insurance.data.dto.InsuranceFilterDto;
import com.mivdhi.insurance.data.dto.InsuranceListDto;
import com.mivdhi.insurance.data.dto.SuccessDto;
import com.mivdhi.insurance.data.dto.UserDto;
import com.mivdhi.insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance")
@CrossOrigin(origins = "http://localhost:3000")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<SuccessDto> createInsurance (@RequestBody Insurance insurance){
        return insuranceService.createInsurance(insurance);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/list")
    public Mono<List<Insurance>> getInsuranceList (){
        return insuranceService.getInsuranceList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/search")
    public Mono<List<Insurance>> searchInsurance (@RequestParam String searchTerm){
        return insuranceService.getSearchedInsurance(searchTerm);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/filter")
    public Mono<List<Insurance>> filterInsurance (@RequestBody InsuranceFilterDto insuranceFilterDto){
        return insuranceService.getFilteredInsurance(insuranceFilterDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/user")
    public Mono<SuccessDto> loginUser (@RequestBody UserDto userDto){
        return insuranceService.loginuserValidation(userDto);
    }
}
