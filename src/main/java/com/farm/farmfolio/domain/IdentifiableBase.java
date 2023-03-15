package com.farm.farmfolio.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@Data
public class IdentifiableBase {
	   @Id
	    @GeneratedValue(generator = "custom-generator",
	            strategy = GenerationType.IDENTITY)
	    @GenericGenerator(
	            name = "custom-generator",
	            strategy = "com.farm.farmfolio.domain.BaseIdentifierGenerator")
	    protected String id;
}



