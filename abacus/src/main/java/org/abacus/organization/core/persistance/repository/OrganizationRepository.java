package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, String> {

	@Query("select c from OrganizationEntity c where c.parent is null order by c.id")
	List<OrganizationEntity> findRootOrganization();

	@Query("select c from OrganizationEntity c left outer join fetch c.customer r where (c.id = :organization or c.id like :organization || '.%') order by c.id")
	List<OrganizationEntity> findByOrganizationTree(@Param("organization")String organization);

	@Query("select c from OrganizationEntity c left outer join fetch c.customer r, SecUserOrganizationEntity u where u.user.id = :username and u.organization.id = c.id order by c.id")
	List<OrganizationEntity> findByUsername(@Param("username")String username);

	@Query("select c from OrganizationEntity c left outer join fetch c.fiscalYearSet f left outer join fetch c.customer r where c.id = :id")
	OrganizationEntity fetchOrganization(@Param("id")String id);

}
