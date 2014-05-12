package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.DefItemDao;
import org.abacus.definition.core.persistance.repository.DefItemRepository;
import org.abacus.definition.shared.ItemAlreadyExistsException;
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
	
	@Autowired
	private DefItemRepository itemRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public ItemCreatedEvent newItem(CreateItemEvent event) throws ItemAlreadyExistsException {
		String userCreated = event.getCreatedUser();
		DefItemEntity item = event.getItem();

		DefItemEntity existingItem = itemRepository.exists(item.getCode(),item.getType().getId(),item.getOrganization().getId());
		if(existingItem != null){
			throw new ItemAlreadyExistsException();
		}
		
		item.createHook(userCreated);
		
		item = itemRepository.save(item);
		
		return new ItemCreatedEvent(item);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public ItemUpdatedEvent updateItem(UpdateItemEvent event) throws ItemAlreadyExistsException {
		String userUpdated = event.getUserUpdated();
		DefItemEntity item = event.getItem();

		DefItemEntity existingItem = itemRepository.exists(item.getCode(),item.getType().getId(),item.getOrganization().getId());
		boolean isItemExists = existingItem != null && !(existingItem.getId().equals(item.getId()));
		if(isItemExists){
			throw new ItemAlreadyExistsException();
		}
		
		item.updateHook(userUpdated);
		
		item = itemRepository.save(item);
		
		return new ItemUpdatedEvent(item);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public ReadItemEvent findItem(RequestReadItemEvent event) {
	
		ItemSearchCriteria searchCriteria = event.getItemSearchCriteria();
		if(searchCriteria != null){
			List<DefItemEntity> resultList = itemDao.requestItems(searchCriteria);
			Integer totalCount = itemDao.itemCount(searchCriteria);
			return new ReadItemEvent(resultList,totalCount);
		}else if(event.getItemId() != null){
			DefItemEntity itemEntity = itemRepository.findWithFetch(event.getItemId());
			itemEntity.getCategory().getName();
			itemEntity.getUnitGroup().getName();
			return new ReadItemEvent(itemEntity);
		}
		
		return null;
	}
	

}
