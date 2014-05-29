package org.abacus.catering.core.persistance.repository;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<CatMenuEntity,Long>{

}
