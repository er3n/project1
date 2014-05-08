package org.abacus.stock.core;

import org.abacus.stock.shared.event.CreateItemEvent;
import org.abacus.stock.shared.event.ItemCreatedEvent;
import org.abacus.stock.shared.event.ItemUpdatedEvent;
import org.abacus.stock.shared.event.ReadItemEvent;
import org.abacus.stock.shared.event.RequestReadItemEvent;
import org.abacus.stock.shared.event.UpdateItemEvent;

public interface ItemService {
	
	public ItemCreatedEvent newItem(CreateItemEvent event);
	
	public ItemUpdatedEvent updateItem(UpdateItemEvent event);
	
	public ReadItemEvent findItem(RequestReadItemEvent event);

}
