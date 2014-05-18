package org.abacus.definition.core.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.abacus.definition.core.persistance.DefItemDao;
import org.abacus.definition.core.persistance.repository.DefItemProductRepository;
import org.abacus.definition.core.persistance.repository.DefItemRepository;
import org.abacus.definition.core.persistance.repository.DefItemUnitRepository;
import org.abacus.definition.shared.ItemAlreadyExistsException;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.abacus.definition.shared.entity.DefItemUnitEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.CreateItemProductEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemProductCreatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.shared.event.ItemProductUptatedEvent;
import org.abacus.definition.web.shared.event.UpdateItemProductEvent;
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
	
	@Autowired
	private DefItemUnitRepository itemUnitRepository;
	
	@Autowired
	private DefItemProductRepository itemProductRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public ItemCreatedEvent newItem(CreateItemEvent event) throws ItemAlreadyExistsException {
		String userCreated = event.getCreatedUser();
		DefItemEntity item = event.getItem();
		Set<DefUnitCodeEntity> unitCodeSet = event.getUnitCodeSet();

		DefItemEntity existingItem = itemRepository.exists(item.getCode(),item.getType().getId(),item.getOrganization().getId());
		if(existingItem != null){
			throw new ItemAlreadyExistsException();
		}
		
		item.createHook(userCreated);
		
		item = itemRepository.save(item);
		
		Set<DefItemUnitEntity> itemUnitSet = new HashSet<>();
		for(DefUnitCodeEntity unitCode : unitCodeSet){
			DefItemUnitEntity itemUnitEntity = new DefItemUnitEntity();
			itemUnitEntity.setItem(item);
			itemUnitEntity.setUnitCode(unitCode);
			itemUnitEntity.createHook(userCreated);
			itemUnitSet.add(itemUnitEntity);
		}
		
		itemUnitRepository.save(itemUnitSet);

		return new ItemCreatedEvent(item);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public ItemUpdatedEvent updateItem(UpdateItemEvent event) throws ItemAlreadyExistsException {
		String userUpdated = event.getUserUpdated();
		DefItemEntity item = event.getItem();
		String organization = event.getOrganization();
		Set<DefUnitCodeEntity> unitCodeSet = event.getUnitCodeSet();

		DefItemEntity existingItem = itemRepository.exists(item.getCode(),item.getType().getId(),organization);
		boolean isItemExists = existingItem != null && !(existingItem.getId().equals(item.getId()));
		if(isItemExists){
			throw new ItemAlreadyExistsException();
		}
		
		item.updateHook(userUpdated);
		
		item = itemRepository.save(item);
		
		itemUnitRepository.delete(item.getId());
		
		Set<DefItemUnitEntity> itemUnitSet = new HashSet<>();
		for(DefUnitCodeEntity unitCode : unitCodeSet){
			DefItemUnitEntity itemUnitEntity = new DefItemUnitEntity();
			itemUnitEntity.setItem(item);
			itemUnitEntity.setUnitCode(unitCode);
			itemUnitEntity.createHook(userUpdated);
			itemUnitSet.add(itemUnitEntity);
		}
		itemUnitRepository.save(itemUnitSet);
		
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

	@Override
	public ItemProductCreatedEvent newItemProduct(CreateItemProductEvent createItemProductEvent) {
		String userCreated = createItemProductEvent.getCreatedUser();
		DefItemProductEntity product = createItemProductEvent.getProduct();
		
		product.createHook(userCreated);
		
		product = itemProductRepository.save(product);
		
		return new ItemProductCreatedEvent(product);
	}

	@Override
	public ItemProductUptatedEvent updateItemProduct(UpdateItemProductEvent updateItemProductEvent) {
		String userUpdated = updateItemProductEvent.getUserUpdated();
		DefItemProductEntity product = updateItemProductEvent.getProduct();
		
		product.updateHook(userUpdated);
		
		product = itemProductRepository.save(product);
		
		return new ItemProductUptatedEvent(product);
	}
	

}
