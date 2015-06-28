package org.abacus.common.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.FinTransactionDao;
import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DocumentFinSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{finTransactionHandler}")
	private TraTransactionHandler<FinDocumentEntity, FinDetailEntity> transactionHandler;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	@ManagedProperty(value = "#{finTransactionDao}")
	private FinTransactionDao finTransactionDao;
	
	@ManagedProperty(value = "#{finDetailRepository}")
	private FinDetailRepository finDetailRepository;
	
	private Map<String, List<FinDocumentEntity>> resultMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
	}

	public List<FinDocumentEntity> getDocumentList(EnumList.DefTypeEnum typeEnum) {
		if (typeEnum==null){
			return new ArrayList<FinDocumentEntity>();
		}
		
		String organization = sessionInfoHelper.currentOrganization().getId();
		String key = organization+"-"+typeEnum.getName();
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			TraDocumentSearchCriteria documentSearchCriteria = new TraDocumentSearchCriteria();
			documentSearchCriteria.setDocType(typeEnum);

			ReadDocumentEvent<FinDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(finTransactionDao, new RequestReadDocumentEvent<FinDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
			List<FinDocumentEntity> documentSearchResultList = readDocumentEvent.getDocumentList();
			resultMap.put(key, documentSearchResultList);
			return documentSearchResultList;
		}
	}

	public TraTransactionHandler<FinDocumentEntity, FinDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<FinDocumentEntity, FinDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

}