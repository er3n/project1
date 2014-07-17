package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserDepartmentRepository  extends CrudRepository<SecUserDepartmentEntity, Long>{

	@Modifying
	@Transactional
	@Query("delete from SecUserDepartmentEntity e where e.department.id = :departmentId")
	void deleteDepartmentUsers(@Param("departmentId")Long departmentId);

	@Modifying
	@Transactional
	@Query("delete from SecUserDepartmentEntity e where e.user.id = :username")
	void deleteUserDepartments(@Param("username")String username);
	
	@Query("select c from SecUserDepartmentEntity c where c.user.id = :username and c.department.organization.id like :organizationId order by id")
	List<SecUserDepartmentEntity> findUserDepartmentList(@Param("username")String username, @Param("organizationId")String organizationId);

}
