package com.mjaraujo.pocgra.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Studio.class)
public abstract class Studio_ {

	public static volatile SingularAttribute<Studio, String> name;
	public static volatile SingularAttribute<Studio, Long> id;
	public static volatile ListAttribute<Studio, Nomination> nominations;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String NOMINATIONS = "nominations";

}

