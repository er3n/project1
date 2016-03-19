package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends Repository<SecAuthorityEntity, String>{

	@Query("select a from SecAuthorityEntity a order by a.code")
	List<SecAuthorityEntity> findAllOrderByCode();
	
	@Query("select distinct(a.id) from SecUserGroupEntity ur, SecGroupAuthorityEntity ra, SecAuthorityEntity a where ur.user.id = :id and ur.group.id = ra.group.id and ra.authority.id = a.id")
	List<String> findUserAuthorities(@Param("id") String id);

	@Query("select a from SecGroupAuthorityEntity ga, SecAuthorityEntity a where a.id = ga.authority.id and ga.group.id = :groupId order by a.code")
	List<SecAuthorityEntity> findGroupAuthorities(@Param("groupId") Long groupId);
}
