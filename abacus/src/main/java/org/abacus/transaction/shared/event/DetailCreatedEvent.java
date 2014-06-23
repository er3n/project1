package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailCreatedEvent<T extends TraDetailEntity> extends CreatedEvent {

	public T detail;
	
	public List<StkDetailTrackEntity> detailTrackList;


	public DetailCreatedEvent(T detail) {
		this.detail = detail;
	}
	
	public DetailCreatedEvent(T detail, List<StkDetailTrackEntity> detailTrackList) {
		this.detail = detail;
		this.detailTrackList = detailTrackList;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

	public List<StkDetailTrackEntity> getDetailTrackList() {
		return detailTrackList;
	}

	public void setDetailTrackList(List<StkDetailTrackEntity> detailTrackList) {
		this.detailTrackList = detailTrackList;
	}

}
