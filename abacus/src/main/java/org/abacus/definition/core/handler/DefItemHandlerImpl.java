package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.DefItemDao;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="defItemHandler")
public class DefItemHandlerImpl implements DefItemHandler{
	
	@Autowired
	private DefItemDao itemDao;

	@Override
	public ItemCreatedEvent newItem(CreateItemEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemUpdatedEvent updateItem(UpdateItemEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public ReadItemEvent findItem(RequestReadItemEvent event) {
	
		ItemSearchCriteria searchCriteria = event.getItemSearchCriteria();
		if(searchCriteria != null){
			List<DefItemEntity> resultList = itemDao.requestItems(searchCriteria);
			return new ReadItemEvent(resultList);
		}
		
		return null;
	}
	

}
