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
import com.sapient.springapp.repository.JdbcRepository;
import com.sapient.springapp.repository.PolicyRepository;

/**
 * Class implements the policy service
 * @author Karthik Rao
 *
 */
@Service("policyService")
@Transactional
@Cacheable(value="policies", cacheManager="cacheManager")
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private JdbcRepository jdbcRepository;
	
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
	
	
	/**
	 * @author Jatin Girhotra
	 * 
	 * Demonstrates the use of custom jdbc repository to construct a sql query dynamically based on the 'input' as explained below:
	 * 
	 * Here are the details :

		1)	 custom JDBCRepository that will be scanned by spring data framework along with other JPA repositories .  
		
		2)	This custom repository has a finder method that will expect column name(s) and value(s) from UI (for example ). We can choose to send 1 or more where clause and respective values
		
		e.g. 
		Please  note, the rest URL  is not an ideal REST URL standard to pass parameter, but I wanted a quick way to focus on core problem e.g. dynamic sql . 
		
		•	http://localhost:8080/VOYAAPP/policy/customdata/policy_name:Retirement:description:Retirement      (i.e. column name , value)
		end query :
		SELECT p1.POLICY_NAME as policyname, p2.description from policy p1 inner join  policy p2 where p1.id=p2.id and p1.policy_name like 'Retirement%' and p1.description like 'Retirement%'
		
		
		•	http://localhost:8080/VOYAAPP/policy/customdata/policy_name:Retirement      (i.e. column name , value)
		end query :
		SELECT p1.POLICY_NAME as policyname, p2.description from policy p1 inner join  policy p2 where p1.id=p2.id and p1.policy_name like 'Retirement%' 
		
		                                I have created a simple join with the same table. This join can be with any table in your database schema. 
		
		3)	The result is a custom POJO object Custom.class which is not an entity. This can be any result set you want to expose to caller.  

	 * 
	 * 
	 */
	@Override
    public List<Custom> findCustomData(String input) {
           //return Lists.newArrayList(policyRepository.findTop2By());
           return Lists.newArrayList(jdbcRepository.getSomeGenericReport(input));
           
    }      

	
}
