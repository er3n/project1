package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, String> {

	@Query("select c from CompanyEntity c where c.id like :company% order by id")
	List<OrganizationEntity> findByCompany(@Param("company")String company);

	@Query("select c from CompanyEntity c, SecUserCompanyEntity uc where uc.user.id = :username and uc.company.id = c.id order by c.id")
	List<OrganizationEntity> findByUsername(@Param("username")String username);

//	@Query("select c from CompanyEntity c where :company like c.id% and c.level < :levelEnum order by c.id desc")
//	List<CompanyEntity> findParentCompany(@Param("company")String company, @Param("levelEnum")EnumList.OrgCompanyLevelEnum levelEnum);
	
}
