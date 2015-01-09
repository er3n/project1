package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;

public class ParentOrganizationNotFoundException extends AbcBusinessException {

	public ParentOrganizationNotFoundException(String... params) {
		super(params);
	}
	
}
