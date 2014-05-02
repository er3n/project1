package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.organization.shared.entity.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends CrudRepository<CompanyEntity, String> {
	                      
	@Query("select g from CompanyEntity g order by name")
	List<CompanyEntity> findAllOrderByName();

	@Query("select c from CompanyEntity c where c.id like :company% order by id")
	List<CompanyEntity> findByCompany(@Param("company")String company);

	@Query("select c from CompanyEntity c, SecUserCompanyEntity uc where uc.user.id = :username and uc.company.id = c.id order by c.id")
	List<CompanyEntity> findByUsername(@Param("username")String username);
	
}
