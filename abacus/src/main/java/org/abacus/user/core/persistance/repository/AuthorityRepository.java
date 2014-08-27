package org.abacus.user.core.persistance.repository;

import java.util.List;

import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AuthorityRepository extends Repository<SecAuthorityEntity, String>{

	@Query("select a from SecAuthorityEntity a order by a.code")
	List<SecAuthorityEntity> findAllOrderByCode();
	
}
