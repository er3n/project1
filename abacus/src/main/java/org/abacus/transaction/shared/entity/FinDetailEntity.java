package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fin_detail")
@SuppressWarnings("serial")
public class FinDetailEntity extends TraDetailEntity {

	@Column(name = "fin_note", nullable = true)
	private String finNote;
	
	
	public FinDetailEntity() {
	}


	public String getFinNote() {
		return finNote;
	}


	public void setFinNote(String finNote) {
		this.finNote = finNote;
	}

	

}
