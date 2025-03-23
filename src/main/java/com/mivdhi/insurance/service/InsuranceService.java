package com.mivdhi.insurance.service;

import com.mivdhi.insurance.data.dao.Insurance;
import com.mivdhi.insurance.data.dto.InsuranceFilterDto;
import com.mivdhi.insurance.data.dto.SuccessDto;
import com.mivdhi.insurance.data.dto.UserDto;
import com.mivdhi.insurance.data.helper.UpdateInfo;
import com.mivdhi.insurance.repository.InsuranceRepo;
import com.mivdhi.insurance.repository.InsuranceTemplateRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepo insuranceRepo;

    private final InsuranceTemplateRepo insuranceTemplateRepo;

    public static String getId(String appendId) {
        ObjectId objectId = new ObjectId();
        return objectId.toString() + appendId;
    }


    /**
     * Creates a new insurance entry in the database.
     *
     * @param insurance The insurance object to be created.
     * @return A Mono containing a SuccessDto indicating the creation status.
     */


    public Mono<SuccessDto> createInsurance(Insurance insurance) {
        insurance.setUpdateInfo(new UpdateInfo());
        insurance.setInsuranceId(getId("IS"));
        return insuranceRepo.save(insurance).map(_ -> new SuccessDto("Insurance Created"));
    }

    /**
     * Retrieves the list of all insurance records.
     *
     * @return A Mono containing a list of all Insurance objects.
     */

    public Mono<List<Insurance>> getInsuranceList() {
        return insuranceRepo.findAll().collectList();
    }

    /**
     * Searches for insurance records based on the provided search term.
     *
     * @param searchTerm The term used to search for matching insurance records.
     * @return A Mono containing a list of Insurance objects that match the search term.
     */

    public Mono<List<Insurance>> getSearchedInsurance(String searchTerm) {
        return insuranceTemplateRepo.searchInsurance(searchTerm);
    }

    /**
     * Retrieves a list of insurance records that match the given filter criteria.
     *
     * @param insuranceFilterDto The filter criteria for retrieving insurance records.
     * @return A Mono containing a list of filtered Insurance objects.
     */

    public Mono<List<Insurance>> getFilteredInsurance(InsuranceFilterDto insuranceFilterDto) {
        return insuranceTemplateRepo.getFilteredInsurance(insuranceFilterDto);
    }

    /**
     * Validates user credentials for login.
     *
     * @param userDto The user credentials containing username and password.
     * @return A Mono containing a SuccessDto indicating either a successful login or invalid credentials.
     */
    public Mono<SuccessDto> loginuserValidation(UserDto userDto) {
        if ("admin@mivdhi.com".equals(userDto.getUserName()) && "Temp@1234".equals(userDto.getPassword())) {
            return Mono.just(new SuccessDto("Success"));
        } else {
            return Mono.just(new SuccessDto("Invalid credentials"));
        }
    }


}
