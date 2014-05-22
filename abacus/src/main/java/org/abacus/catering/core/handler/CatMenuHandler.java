package org.abacus.catering.core.handler;

import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.MenuSummary;

public interface CatMenuHandler {

	public MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria);
	
}
