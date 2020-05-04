package com.bug.spring.security.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class RoleJpaConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role attribute) {
		if(attribute==null) {
			return null;
		}
		else {
			return attribute.getCode();
		}
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		if(dbData==null) {
			return null;
		}
		else {
			return Role.FromCodeToRole(dbData);
		}
	}

}
