package org.abacus.definition.core.handler;

import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;

public interface DefItemHandler {

	public ItemCreatedEvent newItem(CreateItemEvent event);

	public ItemUpdatedEvent updateItem(UpdateItemEvent event);

	public ReadItemEvent findItem(RequestReadItemEvent event);

}
