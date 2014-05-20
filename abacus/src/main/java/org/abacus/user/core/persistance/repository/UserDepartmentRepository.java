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
	@Query("delete from SecUserDepartmentEntity e where e.user.id = :username")
	void deleteAll(@Param("username")String username);
	
	@Query("select c from SecUserDepartmentEntity c where c.department.id = :departmentId order by id")
	List<SecUserDepartmentEntity> findDepartmentUserList(@Param("departmentId")Long departmentId);


}
