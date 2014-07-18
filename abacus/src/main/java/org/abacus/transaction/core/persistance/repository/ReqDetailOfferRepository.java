package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReqDetailOfferRepository extends CrudRepository<ReqDetailOfferEntity, Long> {

	//select d from ReqDetailEntity d inner join fetch d.offerSet o where d.document.id = :documentId and o.isSelected = true
	@Query("select o from ReqDetailOfferEntity o inner join fetch o.detail d where d.document.id = :documentId and o.isSelected = true")
	List<ReqDetailOfferEntity> findSelectedVendorDetailsByDocument(@Param("documentId")Long document);
	
}
 