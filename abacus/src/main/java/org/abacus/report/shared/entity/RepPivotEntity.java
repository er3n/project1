package org.abacus.report.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "rep_pivot")
public class RepPivotEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "sql_text", nullable = false, length=2000)
	private String sqlText;
	
	@Column(name = "field_col_list", nullable = true, length=500)
	private String fieldColList;

	@Column(name = "field_row_list", nullable = true, length=500)
	private String fieldRowList;

	@Column(name = "field_val_list", nullable = true, length=30)
	private String fieldValList;

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getFieldColList() {
		return fieldColList;
	}

	public void setFieldColList(String fieldColList) {
		this.fieldColList = fieldColList;
	}

	public String getFieldRowList() {
		return fieldRowList;
	}

	public void setFieldRowList(String fieldRowList) {
		this.fieldRowList = fieldRowList;
	}

	public String getFieldValList() {
		return fieldValList;
	}

	public void setFieldValList(String fieldValList) {
		this.fieldValList = fieldValList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
