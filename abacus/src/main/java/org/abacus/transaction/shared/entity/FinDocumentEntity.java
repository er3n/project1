package org.abacus.transaction.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "fin_document")
@SuppressWarnings("serial")
public class FinDocumentEntity extends TraDocumentEntity {

	@OneToMany(mappedBy = "finInfo", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<VFinInfoEntity> finInfoSet;
	
	//ref:fin:Payment/Receipt ten Faturaraya
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;
	
	public FinDocumentEntity() {
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

	public Set<VFinInfoEntity> getFinInfoSet() {
		return finInfoSet;
	}

	public void setFinInfoSet(Set<VFinInfoEntity> finInfoSet) {
		this.finInfoSet = finInfoSet;
	}

	public VFinInfoEntity getFinInfo() {
		if (finInfoSet==null || finInfoSet.isEmpty()){
			return new VFinInfoEntity();
		}
		return finInfoSet.iterator().next();
	}

}
