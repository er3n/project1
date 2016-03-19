package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.SecUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SecUserEntity, String> {

	
}
