package org.abacus.report.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public class ReadReportEvent extends ReadEvent {

	private List<StkDetailEntity> detailList;

	public ReadReportEvent(List<StkDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public List<StkDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDocumentList(List<StkDetailEntity> detailList) {
		this.detailList = detailList;
	}

}
