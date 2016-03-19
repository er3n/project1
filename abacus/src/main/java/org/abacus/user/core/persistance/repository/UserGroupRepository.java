package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecUserGroupEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserGroupRepository extends CrudRepository<SecUserGroupEntity, Long> {

	@Query("select count(1) from SecUserGroupEntity e where e.group.id = :groupId")
	Long userCount(@Param("groupId") Long groupId);

	@Modifying
	@Transactional
	@Query("delete from SecUserGroupEntity e where e.user.id = :username and e.group.id>1")
	void delete(@Param("username")String username);
	                      
	
}
