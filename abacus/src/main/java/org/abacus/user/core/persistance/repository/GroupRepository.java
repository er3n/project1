package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecGroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends CrudRepository<SecGroupEntity, Long> {
	                      
	@Query("select g from SecGroupEntity g where g.id>1 order by name")
	List<SecGroupEntity> findAllGroup();

	@Query("select g from SecGroupEntity g where g.name = :name")
	SecGroupEntity findByName(@Param("name")String name);
	
	@Query("select distinct(g) from SecUserGroupEntity gm, SecGroupEntity g where gm.user.id = :username and gm.group.id = g.id order by g.id")
	List<SecGroupEntity> findUserGroup(@Param("username") String username);
	
}
