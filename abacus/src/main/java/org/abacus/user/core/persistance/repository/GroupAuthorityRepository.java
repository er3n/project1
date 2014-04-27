package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecGroupAuthorityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupAuthorityRepository extends CrudRepository<SecGroupAuthorityEntity, Long> {

	@Query("delete from SecGroupAuthorityEntity e where e.group.id = :groupId")
	void deleteByGroupId(@Param("groupId")Long groupId);
	                      

	
}
