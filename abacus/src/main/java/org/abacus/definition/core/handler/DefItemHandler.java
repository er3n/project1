package org.abacus.definition.core.handler;

import org.abacus.definition.shared.ItemAlreadyExistsException;
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

public interface DefItemHandler {

	public ItemCreatedEvent newItem(CreateItemEvent event) throws ItemAlreadyExistsException;

	public ItemUpdatedEvent updateItem(UpdateItemEvent event) throws ItemAlreadyExistsException;

	public ReadItemEvent findItem(RequestReadItemEvent event);

	public ItemProductCreatedEvent newItemProduct(CreateItemProductEvent createItemProductEvent);

	public ItemProductUpdatedEvent updateItemProduct(UpdateItemProductEvent updateItemProductEvent);

	public ItemProductDeletedEvent deleteItemProduct(DeleteItemProductEvent deleteItemProductEvent);

}
