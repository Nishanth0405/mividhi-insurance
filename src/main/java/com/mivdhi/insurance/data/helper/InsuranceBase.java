package com.mivdhi.insurance.data.helper;

import com.mivdhi.insurance.data.enums.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceBase {

    private String name;
    private Double premium;
    private Double coverage;
    private InsuranceType type;
}
