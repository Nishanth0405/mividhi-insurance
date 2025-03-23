package com.mivdhi.insurance.data.dao;


import com.mivdhi.insurance.data.helper.InsuranceBase;
import com.mivdhi.insurance.data.helper.UpdateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Insurance extends InsuranceBase {

    @Id
    private String insuranceId;
    private UpdateInfo updateInfo;
}
