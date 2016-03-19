package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<SecUserEntity, String> {

	@Query("select distinct(a.id) from SecUserGroupEntity ur, SecGroupAuthorityEntity ra, SecAuthorityEntity a where ur.user.id = :id and ur.group.id = ra.group.id and ra.authority.id = a.id")
	public List<String> findUserAuthorities(@Param("id") String id);

	@Query("select distinct(g) from SecUserGroupEntity gm, SecGroupEntity g where gm.user.id = :username and gm.group.id = g.id order by g.id")
	public List<SecGroupEntity> findUserGroups(@Param("username") String username);
	
}
