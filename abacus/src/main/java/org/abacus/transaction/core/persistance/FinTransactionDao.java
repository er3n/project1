package org.abacus.transaction.core.persistance;

import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.springframework.stereotype.Service;



@Service
public class FinTransactionDao extends TraTransactionDao<FinDocumentEntity, FinDetailEntity> {

	@Override
	public Class<FinDocumentEntity> getDocumentClass(){
		return FinDocumentEntity.class;
	}

	@Override
	public Class<FinDetailEntity> getDetailClass(){
		return FinDetailEntity.class;
	}
}
