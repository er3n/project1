package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecGroupMemberEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GroupMemberRepository extends CrudRepository<SecGroupMemberEntity, Long> {

	@Query("select count(1) from SecGroupMemberEntity e where e.group.id = :groupId")
	Long userCount(@Param("groupId") Long groupId);

	@Modifying
	@Transactional
	@Query("delete from SecGroupMemberEntity e where e.user.id = :username")
	void delete(@Param("username")String username);
	                      
	
}
