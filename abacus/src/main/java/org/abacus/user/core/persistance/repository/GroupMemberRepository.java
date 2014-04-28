package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecGroupMemberEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupMemberRepository extends CrudRepository<SecGroupMemberEntity, Long> {

	@Query("select count(1) from SecGroupMemberEntity e where e.group.id = :groupId")
	Long userCount(@Param("groupId") Long groupId);
	                      
	
}
