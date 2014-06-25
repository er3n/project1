package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailCreatedEvent<D extends TraDetailEntity<D>> extends CreatedEvent {

	public D detail;
	
	public List<StkDetailTrackEntity> detailTrackList;


	public DetailCreatedEvent(D detail) {
		this.detail = detail;
	}
	
	public DetailCreatedEvent(D detail, List<StkDetailTrackEntity> detailTrackList) {
		this.detail = detail;
		this.detailTrackList = detailTrackList;
	}

	public D getDetail() {
		return detail;
	}

	public void setDetail(D detail) {
		this.detail = detail;
	}

	public List<StkDetailTrackEntity> getDetailTrackList() {
		return detailTrackList;
	}

	public void setDetailTrackList(List<StkDetailTrackEntity> detailTrackList) {
		this.detailTrackList = detailTrackList;
	}

}
