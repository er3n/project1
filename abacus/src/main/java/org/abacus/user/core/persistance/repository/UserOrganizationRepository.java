package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecUserOrganizationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserOrganizationRepository extends CrudRepository<SecUserOrganizationEntity, Long> {

	@Modifying
	@Transactional
	@Query("delete from SecUserOrganizationEntity e where e.user.id = :username")
	void delete(@Param("username") String username);

	@Modifying
	@Transactional
	@Query("delete from SecUserOrganizationEntity e where e.user.id = :username  and e.organization.id = :org")
	void deleteByNameAndOrg(@Param("username") String username, @Param("org") String org);
	
	@Query("select e from SecUserOrganizationEntity e where e.user.id = :username and e.organization.id = :org")
	SecUserOrganizationEntity findByNameAndOrg(@Param("username") String username, @Param("org") String org);
}
