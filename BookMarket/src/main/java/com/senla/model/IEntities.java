package com.senla.model;

import annitations.Prototype;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = IEntities.PROPERTY)
@JsonSubTypes(value = { @JsonSubTypes.Type(Book.class), @JsonSubTypes.Type(Order.class), @JsonSubTypes.Type(Request.class) })
@JsonRootName(value = "data")
@Prototype
public abstract class IEntities {

    protected static final String PROPERTY = "type";

    abstract public int getId();

    public IEntities(){};
}
