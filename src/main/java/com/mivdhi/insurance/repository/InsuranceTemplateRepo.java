package com.mivdhi.insurance.repository;


import com.mivdhi.insurance.data.dao.Insurance;
import com.mivdhi.insurance.data.dto.InsuranceFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InsuranceTemplateRepo {

    private final ReactiveMongoTemplate reactiveMongoTemplate;


    /**
     * Searches for insurance records based on a search term.
     * The search is performed on fields such as `name`, `type`, `premium`, and `coverage`
     * using case-insensitive regex matching.
     *
     * @param searchTerm The term used to search for matching insurance records.
     * @return A Mono containing a list of Insurance objects that match the search term.
     */

    public Mono<List<Insurance>> searchInsurance(String searchTerm) {
        List<Criteria> criteriaList = new ArrayList<>();


        // Add regex search for string fields (excluding `_id`)
        criteriaList.add(Criteria.where("name").regex(searchTerm, "i"));
        criteriaList.add(Criteria.where("type").regex(searchTerm, "i"));
        criteriaList.add(Criteria.where("premium").regex(searchTerm, "i"));
        criteriaList.add(Criteria.where("coverage").regex(searchTerm, "i"));

        MatchOperation matchStage = Aggregation.match(new Criteria().orOperator(criteriaList.toArray(new Criteria[0])));

        Aggregation aggregation = Aggregation.newAggregation(matchStage);

        return reactiveMongoTemplate.aggregate(aggregation, "insurance", Insurance.class).collectList();
    }

    /**
     * Retrieves a filtered list of insurance records based on various criteria.
     * Supports filtering by:
     * - Search term (case-insensitive)
     * - Premium range (min/max)
     * - Coverage range (min/max)
     * - Insurance types
     * - Sorting by premium (ascending/descending)
     * - Pagination (page number and count per page)
     *
     * @param insuranceFilterDto The filter criteria for retrieving insurance records.
     * @return A Mono containing a list of filtered Insurance objects.
     */


    public Mono<List<Insurance>> getFilteredInsurance (InsuranceFilterDto insuranceFilterDto){
        Criteria criteria = new Criteria();

        List<AggregationOperation> stages = new ArrayList<>();

        if (insuranceFilterDto.getSearchTerm() != null && !insuranceFilterDto.getSearchTerm().isEmpty()) {
            criteria.orOperator(
                    Criteria.where("name").regex(insuranceFilterDto.getSearchTerm(), "i"),
//                    Criteria.where("type").regex(insuranceFilterDto.getSearchTerm(), "i"),
                    Criteria.where("premium").regex(insuranceFilterDto.getSearchTerm(), "i"),
                    Criteria.where("coverage").regex(insuranceFilterDto.getSearchTerm(), "i")
            );
        }

        if (insuranceFilterDto.getMinimumPremium() != null && insuranceFilterDto.getMaximumPremium() != null) {
            criteria.and("premium").gte(insuranceFilterDto.getMinimumPremium()).lte(insuranceFilterDto.getMaximumPremium());
        }

        if (insuranceFilterDto.getMinimumCoverage() != null && insuranceFilterDto.getMaximumCoverage() != null) {
            criteria.and("coverage").gte(insuranceFilterDto.getMinimumCoverage()).lte(insuranceFilterDto.getMaximumCoverage());
        }

        if (insuranceFilterDto.getTypes() != null && !insuranceFilterDto.getTypes().isEmpty()) {
            criteria.and("type").in(insuranceFilterDto.getTypes());
        }

        MatchOperation matchStage = Aggregation.match(criteria);

        Sort.Direction direction = "desc".equalsIgnoreCase(insuranceFilterDto.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;

        SortOperation sortStage = Aggregation.sort(direction, "premium");

        if(insuranceFilterDto.getPage() != null && insuranceFilterDto.getCount() != null) {
            int skipRecords = (insuranceFilterDto.getPage() - 1) * insuranceFilterDto.getCount();
            SkipOperation skipStage = Aggregation.skip(skipRecords);
            LimitOperation limitStage = Aggregation.limit(insuranceFilterDto.getCount());
            stages.add(skipStage);
            stages.add(limitStage);

        }
        stages.add(matchStage);
        stages.add(sortStage);

        Aggregation aggregation = Aggregation.newAggregation(stages);


        return reactiveMongoTemplate.aggregate(aggregation, "insurance", Insurance.class).collectList();
    }
}
