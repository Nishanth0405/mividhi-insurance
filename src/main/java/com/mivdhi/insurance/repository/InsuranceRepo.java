package com.mivdhi.insurance.repository;

//import com.mivdhi.insurance.data.dao.InsuranceBas;
import com.mivdhi.insurance.data.dao.Insurance;
import com.mivdhi.insurance.data.helper.InsuranceBase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepo extends ReactiveMongoRepository<Insurance,String > {
}
