package org.abacus.transaction.core.persistance;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.springframework.stereotype.Service;



@Service
public class StkTransactionDao extends TraTransactionDao<StkDocumentEntity, StkDetailEntity> {

	public Class getDocumentClass(){
		return StkDocumentEntity.class;
	}

}
