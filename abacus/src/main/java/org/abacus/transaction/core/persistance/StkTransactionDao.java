package org.abacus.transaction.core.persistance;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.springframework.stereotype.Service;



@Service
public class StkTransactionDao extends TraTransactionDao<StkDocumentEntity, StkDetailEntity> {

	@Override
	public Class<StkDocumentEntity> getDocumentClass(){
		return StkDocumentEntity.class;
	}

	@Override
	public Class<StkDetailEntity> getDetailClass(){
		return StkDetailEntity.class;
	}

}
