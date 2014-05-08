package org.abacus.stock.web.domain;

import org.abacus.common.shared.holder.KeyValue;
import org.abacus.definition.shared.constant.EnumList;

public class Item {
	
	private KeyValue type;
	
	private String name;
	
	private boolean active;
	
	private EnumList.DefItemClassEnum itemClass;
	
	private KeyValue category;

}

