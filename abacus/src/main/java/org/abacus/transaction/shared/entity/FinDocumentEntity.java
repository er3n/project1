package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fin_document")
@SuppressWarnings("serial")
public class FinDocumentEntity extends TraDocumentEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_", nullable=true)
	private VFinInfoEntity finInfo;

	//ref:fin:Payment/Receipt ten Faturaraya
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;
	
	public FinDocumentEntity() {
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
		this.setFinInfo(new VFinInfoEntity(id));
	}
	
	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

	public VFinInfoEntity getFinInfo() {
		return finInfo;
	}

	public void setFinInfo(VFinInfoEntity finInfo) {
		this.finInfo = finInfo;
	}


}
