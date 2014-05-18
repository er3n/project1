package org.abacus.definition.core.handler;

import org.abacus.definition.shared.ItemAlreadyExistsException;
import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.CreateItemProductEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemProductCreatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.web.shared.event.ItemProductUptatedEvent;
import org.abacus.definition.web.shared.event.UpdateItemProductEvent;

public interface DefItemHandler {

	public ItemCreatedEvent newItem(CreateItemEvent event) throws ItemAlreadyExistsException;

	public ItemUpdatedEvent updateItem(UpdateItemEvent event) throws ItemAlreadyExistsException;

	public ReadItemEvent findItem(RequestReadItemEvent event);

	public ItemProductCreatedEvent newItemProduct(CreateItemProductEvent createItemProductEvent);

	public ItemProductUptatedEvent updateItemProduct(UpdateItemProductEvent updateItemProductEvent);

}
