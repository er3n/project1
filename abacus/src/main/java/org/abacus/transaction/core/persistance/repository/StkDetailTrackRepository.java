package org.abacus.transaction.core.persistance.repository;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StkDetailTrackRepository extends CrudRepository<StkDetailTrackEntity, Long>{

	@Query("select sum(t.baseTrackCount - t.baseUsedCount) from StkDetailTrackEntity t inner join t.detail d where d.fiscalYear.id = :fiscalYearId and d.item.id = :itemId and d.department.id = :departmentId and d.trStateDetail = 1 and t.baseTrackCount > t.baseUsedCount")
	public BigDecimal currentItemCount(@Param("itemId")Long itemId,@Param("departmentId")Long departmentId,@Param("fiscalYearId") String fiscalYearId);
	
	@Query("select t from StkDetailTrackEntity t inner join t.detail d where d.fiscalYear.id = :fiscalYearId and d.item.id = :itemId and d.department.id = :departmentId and d.trStateDetail = 1 and t.baseTrackCount > t.baseUsedCount order by t.lotTrackDate")
	public List<StkDetailTrackEntity> currentItemList(@Param("itemId")Long itemId,@Param("departmentId")Long departmentId,@Param("fiscalYearId") String fiscalYearId);
	
	@Modifying
	@Transactional
	@Query("delete from StkDetailTrackEntity t where t.detail.id = :detailId")
	void deleteDetailTrack(@Param("detailId")Long detailId);

	@Query("select t from StkDetailTrackEntity t left outer join fetch t.parentTrack p where t.detail.id = :detailId")
	public List<StkDetailTrackEntity> findDetailTrack(@Param("detailId")Long detailId);
	
	
}
