package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecGroupAuthorityEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GroupAuthorityRepository extends CrudRepository<SecGroupAuthorityEntity, Long> {

	@Modifying
	@Transactional
	@Query("delete from SecGroupAuthorityEntity e where e.group.id = :groupId")
	void deleteByGroupId(@Param("groupId")Long groupId);
	                      

	
}
