package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stk_detail")
@SuppressWarnings("serial")
public class StkDetailEntity extends TraDetailEntity {

	@Column(name = "stk_note", nullable = true)
	private String stkNote;
	
	public StkDetailEntity() {
	}

	public String getStkNote() {
		return stkNote;
	}

	public void setStkNote(String stkNote) {
		this.stkNote = stkNote;
	}

	

}
