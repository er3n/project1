package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class StkDetailCreatedEvent extends DetailCreatedEvent {

	public List<StkDetailTrackEntity> detailTrackList;

	public StkDetailCreatedEvent(TraDetailEntity detail, List<StkDetailTrackEntity> detailTrackList) {
		super(detail);
		this.detailTrackList = detailTrackList;
	}

	public List<StkDetailTrackEntity> getDetailTrackList() {
		return detailTrackList;
	}

	public void setDetailTrackList(List<StkDetailTrackEntity> detailTrackList) {
		this.detailTrackList = detailTrackList;
	}

}
