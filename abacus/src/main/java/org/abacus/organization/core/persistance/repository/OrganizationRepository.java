package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, String> {

	@Query("select c from OrganizationEntity c where c.id like :organization% order by id")
	List<OrganizationEntity> findByOrganization(@Param("organization")String organization);

	@Query("select c from OrganizationEntity c, SecUserOrganizationEntity uc where uc.user.id = :username and uc.organization.id = c.id order by c.id")
	List<OrganizationEntity> findByUsername(@Param("username")String username);

}
