package org.abacus.definition.web.model;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.web.jsf.FacesContextUtils;

public class ItemDataModel extends LazyDataModel<DefItemEntity> {

	private ItemSearchCriteria searchCriteria;

	private List<DefItemEntity> currentResult;
	
	private DefItemHandler itemHandler;

	public ItemDataModel(ItemSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		FacesContext ctx = FacesContext.getCurrentInstance();
		itemHandler = FacesContextUtils.getWebApplicationContext(ctx).getBean(DefItemHandler.class);
	}

	@Override
	public DefItemEntity getRowData(String rowKey) {
		Long longRowKey = Long.valueOf(rowKey);
		for (DefItemEntity item : currentResult) {
			if (item.getId().equals(longRowKey))
				return item;
		}
		return null;
	}

	@Override
	public Object getRowKey(DefItemEntity item) {
		return item.getId();
	}

	@Override
	public List<DefItemEntity> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		searchCriteria.setFirst(first);
		searchCriteria.setPageSize(pageSize);
		
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(searchCriteria));
		currentResult = readItemEvent.getItemList();
		
		
		int dataSize = searchCriteria.getResultCount().intValue();
		super.setRowCount(dataSize);
		
		 if(dataSize > pageSize) {
	            try {
	                return currentResult.subList(first, first + pageSize);
	            }
	            catch(IndexOutOfBoundsException e) {
	                return currentResult.subList(first, first + (dataSize % pageSize));
	            }
	        }
	        else {
	            return currentResult;
	        }
		
	}

}
