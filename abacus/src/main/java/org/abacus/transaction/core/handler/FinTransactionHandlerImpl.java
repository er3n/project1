package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.springframework.stereotype.Service;

@Service("finTransactionHandler")
public class FinTransactionHandlerImpl extends TraTransactionSupport {

	@Override
	public ReadDetailEvent readDetail(RequestReadDetailEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
