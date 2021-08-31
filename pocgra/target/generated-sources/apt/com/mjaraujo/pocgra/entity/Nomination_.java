package com.mjaraujo.pocgra.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Nomination.class)
public abstract class Nomination_ {

	public static volatile ListAttribute<Nomination, Producer> producerPartners;
	public static volatile SingularAttribute<Nomination, Boolean> winner;
	public static volatile SingularAttribute<Nomination, Integer> year;
	public static volatile ListAttribute<Nomination, Studio> studioPartners;
	public static volatile SingularAttribute<Nomination, Long> id;
	public static volatile SingularAttribute<Nomination, String> movieTitle;

	public static final String PRODUCER_PARTNERS = "producerPartners";
	public static final String WINNER = "winner";
	public static final String YEAR = "year";
	public static final String STUDIO_PARTNERS = "studioPartners";
	public static final String ID = "id";
	public static final String MOVIE_TITLE = "movieTitle";

}

