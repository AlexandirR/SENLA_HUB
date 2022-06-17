package com.senla.repository.interfacedata;

import annitations.Prototype;

@Prototype
public interface RepositoryCrud<T> {

    T create(T item);

    T delete(T item);

    T update(T item);

    T getById(Integer id);
}
