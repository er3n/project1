package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.shared.ItemAlreadyExistsException;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.CreateItemProductEvent;
import org.abacus.definition.shared.event.DeleteItemProductEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemProductCreatedEvent;
import org.abacus.definition.shared.event.ItemProductDeletedEvent;
import org.abacus.definition.shared.event.ItemProductUpdatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.shared.event.UpdateItemProductEvent;
import org.abacus.user.core.handler.UserService;

public interface DefItemHandler {

	public ItemCreatedEvent newItem(CreateItemEvent event, UserService userService) throws ItemAlreadyExistsException;

	public ItemUpdatedEvent updateItem(UpdateItemEvent event, UserService userService) throws ItemAlreadyExistsException;

	public ReadItemEvent findItem(RequestReadItemEvent event);

	public ItemProductCreatedEvent newItemProduct(CreateItemProductEvent createItemProductEvent);

	public ItemProductUpdatedEvent updateItemProduct(UpdateItemProductEvent updateItemProductEvent);

	public ItemProductDeletedEvent deleteItemProduct(DeleteItemProductEvent deleteItemProductEvent);

	DefItemEntity itemExists(String code, String type, String organization);
	
	List<DefItemEntity> loginItemList(String code, String type, String organization);

}
