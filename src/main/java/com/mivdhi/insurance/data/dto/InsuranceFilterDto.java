package com.mivdhi.insurance.data.dto;

import com.mivdhi.insurance.data.dao.Insurance;
import com.mivdhi.insurance.data.enums.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceFilterDto {

    private String searchTerm;
    private List<InsuranceType> types;
    private String sortOrder;
    private Integer page;
    private Integer count;
    private Double minimumPremium;
    private Double maximumPremium;
    private Double minimumCoverage;
    private Double maximumCoverage;
}
