package org.abacus.organization.core.persistance.repository;

import java.util.List;

import org.abacus.organization.shared.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

	@Query("select c from DepartmentEntity c where c.company.id = :company order by code")
	List<DepartmentEntity> findByCompany(@Param("company")String company);

}
