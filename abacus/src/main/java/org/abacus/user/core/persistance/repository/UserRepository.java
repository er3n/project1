package org.abacus.user.core.persistance.repository;

import org.abacus.user.shared.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
