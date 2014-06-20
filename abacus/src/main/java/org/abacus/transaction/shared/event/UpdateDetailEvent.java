package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdateEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class UpdateDetailEvent<T extends TraDetailEntity> extends UpdateEvent {

	private T detail;
	private String user;
	private String organization;

	public UpdateDetailEvent(T detail, String user, String organization) {
		this.detail = detail;
		this.user = user;
		this.organization = organization;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
