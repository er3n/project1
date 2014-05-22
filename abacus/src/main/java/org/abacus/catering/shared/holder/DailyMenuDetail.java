package org.abacus.catering.shared.holder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.abacus.catering.shared.entity.CatMenuEntity;

public class DailyMenuDetail implements Comparable<DailyMenuDetail> {

	private Date date;
	private Map<String, CatMenuEntity> menuMap = new HashMap<String, CatMenuEntity>();

	public DailyMenuDetail(Date date) {
		this.date = date;
	}

	public Map<String, CatMenuEntity> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, CatMenuEntity> menuMap) {
		this.menuMap = menuMap;
	}

	public void putMenu(CatMenuEntity menu) {
		menuMap.put(menu.getMeal().getCode(), menu);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(DailyMenuDetail o) {
		return this.date.compareTo(o.date);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((menuMap == null) ? 0 : menuMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		DailyMenuDetail other = (DailyMenuDetail) obj;
		return this.date.equals(other.getDate());
	}

}
