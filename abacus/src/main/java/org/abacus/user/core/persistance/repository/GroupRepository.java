package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends CrudRepository<SecGroupEntity, Long> {
	                      
	@Query("select g from SecGroupEntity g where g.id>1 order by name")
	List<SecGroupEntity> findAllNormal();//id>1:Zystem haric

	@Query("select g from SecGroupEntity g order by name")
	List<SecGroupEntity> findAllRoot();

	@Query("select a from SecGroupAuthorityEntity ga, SecAuthorityEntity a where a.id = ga.authority.id and ga.group.id = :groupId order by a.code")
	List<SecAuthorityEntity> findGroupAuthorities(@Param("groupId") Long groupId);

	@Query("select g from SecGroupEntity g where lower(g.name) = lower(:name)")
	public SecGroupEntity findByName(@Param("name")String name);
	
}
