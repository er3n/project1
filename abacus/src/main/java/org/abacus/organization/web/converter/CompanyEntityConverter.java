package org.abacus.organization.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.organization.core.persistance.repository.CompanyRepository;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("companyEntityConverter")
public class CompanyEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(StringUtils.isEmpty(value)){
			return null;
		}
		CompanyRepository repository = FacesContextUtils.getWebApplicationContext(context).getBean(CompanyRepository.class);
		OrganizationEntity entitiy = repository.findOne(value);
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value instanceof OrganizationEntity){
			OrganizationEntity entitiy = (OrganizationEntity) value;
			return entitiy.getId();
		}
		return null;
	}

}
