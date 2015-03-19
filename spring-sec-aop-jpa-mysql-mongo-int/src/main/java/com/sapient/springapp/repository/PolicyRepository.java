/**
 * Created on Feb 13, 2015
 */
package com.sapient.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sapient.springapp.domain.Policy;

/**
 * Repository for the policy data
 * @author Karthik Rao
 */
@Repository
public interface PolicyRepository extends PagingAndSortingRepository<Policy, Long> {
	
	public List<Policy> findTop2By();
	
	@Query(value = "SELECT * FROM policy WHERE policy.id<10", nativeQuery = true)
	public List<Policy> findComplex();
	
}