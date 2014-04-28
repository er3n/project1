package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefTypeEntity;

public interface DefTypeHandler extends Serializable{

	List<DefTypeEntity> getTypeList();

	void saveOrUpdateEntity(DefTypeEntity entity);
	
	void deleteEntity(DefTypeEntity entity);

	/*
	T getEntity(I id);

	List<T> getEntityList();

	void saveEntity(T entity);

	void updateEntity(T entity);

	void saveOrUpdateEntity(T entity);

	void deleteEntity(T entity);

	void refreshEntity(T entity);

	List<T> getResultList(String query);

	T getResultSingle(String query);

	Integer executeSql(String sqlText);

	List<T> findByNamedQuery(String queryName, Object... params);
	*/
	
}
