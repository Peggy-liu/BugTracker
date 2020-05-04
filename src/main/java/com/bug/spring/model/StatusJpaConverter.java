package com.bug.spring.model;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
	class StatusJpaConverter implements AttributeConverter<Status, String> {
	 
	
	@Override
	public String convertToDatabaseColumn(Status attribute) {
		if(attribute==null) {
			return null;
		}
		return attribute.getCode();
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		if(dbData==null) {
			return null;
		}
		return Status.fromCodeToStatus(dbData);
	}
	 
	}

