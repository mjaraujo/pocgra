package com.mjaraujo.pocgra.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producer.class)
public abstract class Producer_ {

	public static volatile SingularAttribute<Producer, String> name;
	public static volatile SingularAttribute<Producer, Long> id;
	public static volatile ListAttribute<Producer, Nomination> nominations;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String NOMINATIONS = "nominations";

}

