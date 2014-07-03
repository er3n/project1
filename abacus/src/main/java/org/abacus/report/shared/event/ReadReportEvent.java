package org.abacus.report.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class ReadReportEvent<D extends TraDetailEntity<D>> extends ReadEvent {

	private List<D> detailList;

	public ReadReportEvent(List<D> detailList) {
		this.detailList = detailList;
	}

	public List<D> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<D> detailList) {
		this.detailList = detailList;
	}

}
