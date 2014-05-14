package org.abacus.definition.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.definition.core.persistance.repository.DefUnitGroupRepository;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("unitGroupEntityConverter")
public class UnitGroupEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(!StringUtils.hasText(value)){
			return null;
		}
		DefUnitGroupRepository repository = FacesContextUtils.getWebApplicationContext(context).getBean(DefUnitGroupRepository.class);
		DefUnitGroupEntity entitiy = repository.findOne(Long.valueOf(value));
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(!(value instanceof DefUnitGroupEntity)){
			return "";
		}
		DefUnitGroupEntity entitiy = (DefUnitGroupEntity) value;
		return String.valueOf(entitiy.getId());
	}

}
