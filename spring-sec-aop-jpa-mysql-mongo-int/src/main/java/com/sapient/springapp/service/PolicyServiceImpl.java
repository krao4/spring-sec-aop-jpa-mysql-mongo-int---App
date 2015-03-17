/**
 * Created on Feb 13, 2015
 */
package com.sapient.springapp.service;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sapient.springapp.domain.Custom;
import com.sapient.springapp.domain.Customer;
import com.sapient.springapp.domain.Policy;
import com.sapient.springapp.repository.PolicyRepository;

/**
 * @author Karthik Rao
 *
 */
@Service("policyService")
@Transactional
@Cacheable(value="policies", cacheManager="cacheManager")
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Cacheable(value="policies", cacheManager="cacheManager")
	@Transactional(readOnly=true)
	public List<Policy> findAll() {
		//return Lists.newArrayList(policyRepository.findTop2By());
		List<Policy> abc=Lists.newArrayList(policyRepository.findTop2By());
		return abc;
	}
	
	@Transactional(readOnly=true)
	public List<Policy> findCustom() {
		//return Lists.newArrayList(policyRepository.findTop2By());
		List<Policy> abc=Lists.newArrayList(policyRepository.findComplex());
		return abc;
	}

	@Transactional(readOnly=true)
	@CachePut(value="policies", key="#id", cacheManager="cacheManager")
	public Policy findById(Long id) {
		return policyRepository.findOne(id);
	}

	public Policy save(Policy policy) {
		return policyRepository.save(policy);
	}

	@Transactional(readOnly=true)
	public Page<Policy> findAllByPage(Pageable pageable) {
		return policyRepository.findAll(pageable);
	}	

	//@CacheEvict(value="policies", key="#id", cacheManager="cacheManager")
	public void delete(String id) {
		Policy policy = policyRepository.findOne(Long.parseLong(id));
		policyRepository.delete(policy);
	}
	
	/**
	 * Demonstrate cache cleaning!
	 */
	@CacheEvict(value="policies",  cacheManager="cacheManager", allEntries=true)
	public void cleanCache() {
		//customerRepository.deleteAll();
	}	
	
}
