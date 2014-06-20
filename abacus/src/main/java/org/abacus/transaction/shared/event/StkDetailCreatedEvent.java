package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;

public class StkDetailCreatedEvent extends DetailCreatedEvent<StkDetailEntity> {

	public List<StkDetailTrackEntity> detailTrackList;

	public StkDetailCreatedEvent(StkDetailEntity detail, List<StkDetailTrackEntity> detailTrackList) {
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
