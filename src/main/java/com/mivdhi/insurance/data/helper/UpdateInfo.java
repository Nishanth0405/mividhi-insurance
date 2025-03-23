package com.mivdhi.insurance.data.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInfo {

    private String updatedBy;
    private LocalDateTime updatedOn;
    private String createdBy;
    private LocalDateTime createdOn;
}
