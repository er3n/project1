package org.abacus.catering.core.persistance.repository;

import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MenuItemRepository extends CrudRepository<CatMenuItemEntity,Long>{
	
	@Modifying
	@Transactional
	@Query("delete from CatMenuItemEntity mi where mi.menu.id = :menuId")
	void delete(@Param("menuId")Long menuId);

}
 