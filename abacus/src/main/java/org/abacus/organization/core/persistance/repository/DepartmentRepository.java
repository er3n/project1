package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

	@Query("select c from DepartmentEntity c where c.company.id = :companyId and c.departmentGroup = :groupEnum order by code")
	List<DepartmentEntity> findByCompanyAndGroup(@Param("companyId")String companyId, @Param("groupEnum") EnumList.OrgDepartmentGroupEnum groupEnum);

}
