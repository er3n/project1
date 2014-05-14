package org.abacus.definition.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.definition.core.persistance.repository.DefUnitCodeRepository;
import org.abacus.definition.core.persistance.repository.DefUnitGroupRepository;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("unitCodeEntityConverter")
public class UnitCodeEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(!StringUtils.hasText(value)){
			return null;
		}
		DefUnitCodeRepository repository = FacesContextUtils.getWebApplicationContext(context).getBean(DefUnitCodeRepository.class);
		DefUnitCodeEntity entitiy = repository.findOne(Long.valueOf(value));
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(!(value instanceof DefUnitCodeEntity)){
			return "";
		}
		DefUnitCodeEntity entitiy = (DefUnitCodeEntity) value;
		return String.valueOf(entitiy.getId());
	}

}
