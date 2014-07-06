package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.abacus.definition.shared.constant.EnumList;

@Entity
@Table(name = "req_document")
@SuppressWarnings("serial")
public class ReqDocumentEntity extends TraDocumentEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "request_status", nullable = false)
	private EnumList.RequestStatus requestStatus;

	public EnumList.RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(EnumList.RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

}
