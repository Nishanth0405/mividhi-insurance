package com.mivdhi.insurance.data.dto;


import com.mivdhi.insurance.data.dao.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceListDto {

    private List<Insurance> insuranceList;
}
