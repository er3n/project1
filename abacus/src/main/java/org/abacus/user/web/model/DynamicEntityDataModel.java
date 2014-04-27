package org.abacus.user.web.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.abacus.common.shared.entity.DynamicEntity;
import org.primefaces.model.SelectableDataModel;

public class DynamicEntityDataModel<T extends DynamicEntity> extends ListDataModel<T>
		implements SelectableDataModel<T> {

	public DynamicEntityDataModel() {
	}

	public DynamicEntityDataModel(List<T> dynamicEntityList) {
		super(dynamicEntityList);
	}

	@Override
	public Object getRowKey(T object) {
		Long id = object.getId();
		return id.toString();
	}

	@Override
	public T getRowData(String rowKey) {

		List<T> dynamicEntityList = (List<T>) getWrappedData();

		Long rowKeyLong = Long.valueOf(rowKey);

		for (T entitiy : dynamicEntityList) {
			if (entitiy.getId().equals(rowKeyLong)) {
				return entitiy;
			}

		}
		return null;
	}

}
