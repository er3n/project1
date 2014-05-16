package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentUserRepository extends CrudRepository<SecUserDepartmentEntity, Long> {

	@Query("select c from SecUserDepartmentEntity c where c.department.id = :organizationId order by id")
	List<SecUserDepartmentEntity> findByOrganization(@Param("organizationId")String organizationId);

}
