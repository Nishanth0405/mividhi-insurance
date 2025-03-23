package com.mivdhi.insurance.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum InsuranceType {

    TERM_LIFE ("Term life"),
    HEALTH("Health"),
    VEHICLE("Vehicle");

    @Getter
    private final String displayName;


}
