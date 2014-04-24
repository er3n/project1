package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	@Query("select distinct(a.id) from UserRoleEntity ur, RoleAuthorityEntity ra, AuthorityEntity a where ur.user.id = :id and ur.role.id = ra.id and ra.authority.id = a.id")
	public List<String> findUserAuthorities(@Param("id") String id);
	
}
