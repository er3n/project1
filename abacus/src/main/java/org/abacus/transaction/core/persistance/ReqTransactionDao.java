package org.abacus.transaction.core.persistance;

import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.springframework.stereotype.Service;



@Service
public class ReqTransactionDao extends TraTransactionDao<ReqDocumentEntity, ReqDetailEntity> {

	@Override
	public Class<ReqDocumentEntity> getDocumentClass(){
		return ReqDocumentEntity.class;
	}

	@Override
	public Class<ReqDetailEntity> getDetailClass(){
		return ReqDetailEntity.class;
	}
}
