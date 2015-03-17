package com.sapient.springapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sapient.springapp.domain.Mainnav;


@Repository
public interface MainnavRepository extends MongoRepository<Mainnav, String> {

}
