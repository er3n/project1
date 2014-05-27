package org.abacus.definition.web.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.jsf.FacesContextUtils;

public class ItemDataModel extends LazyDataModel<DefItemEntity> {

	private ItemSearchCriteria searchCriteria;

	private List<DefItemEntity> currentResult;

	private DefItemHandler itemHandler;

	public ItemDataModel(ItemSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		FacesContext ctx = FacesContext.getCurrentInstance();
		itemHandler = FacesContextUtils.getWebApplicationContext(ctx).getBean(
				DefItemHandler.class);
	}

	@Override
	public DefItemEntity getRowData(String rowKey) {
		Long longRowKey = Long.valueOf(rowKey);
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(longRowKey));
		return readItemEvent.getItem();
	}

	@Override
	public Object getRowKey(DefItemEntity item) {
		return item.getId();
	}

	@Override
	public List<DefItemEntity> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchCriteria.setFirst(first);
		searchCriteria.setPageSize(pageSize);
		
		this.addFilters(filters);

		ReadItemEvent readItemEvent = itemHandler
				.findItem(new RequestReadItemEvent(searchCriteria));
		
		this.clearFilters();
		
		currentResult = readItemEvent.getItemList();

		int dataSize = readItemEvent.getTotalCount();
		super.setRowCount(dataSize);

		return currentResult;

	}
	
	private void clearFilters() {
		searchCriteria.setCodeLike(null);
		searchCriteria.setNameLike(null);
		searchCriteria.setStatus(null);
		searchCriteria.setCategoryCodeLike(null);
		
	}

	private void addFilters(Map<String, Object> filters){
		if(!CollectionUtils.isEmpty(filters)){
			
			Set<String> nameSet = filters.keySet();
			
			for(String name : nameSet){
				if(name.equals("code")){
					searchCriteria.setCodeLike((String)filters.get(name));
				}
				if(name.equals("name")){
					searchCriteria.setNameLike((String)filters.get(name));
				}
				if(name.equals("category")){
					searchCriteria.setCategoryCodeLike((String)filters.get(name));
				}
				if(name.equals("active")){
					searchCriteria.setStatus((Boolean)filters.get(name));
				}
			}
			
		}
	}

}
