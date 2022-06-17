package com.senla.repository.jsondata;

import annitations.Prototype;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.model.IEntities;
import com.senla.property.ConfigurationProperty;
import com.senla.repository.interfacedata.RepositoryCrud;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Prototype
public abstract class AbstractRepoData<T extends IEntities> implements RepositoryCrud<T> {

    protected String nameOfFile;
    protected Path path;
    protected ObjectMapper mapper;
    protected List<T> items;

    protected void init() throws IOException {
        this.path = Paths.get(ConfigurationProperty.getInstance().getPropertyByName("data.json.folder") + this.nameOfFile);
        this.mapper = new ObjectMapper();
        this.items = mapper.readValue(this.path.toFile(),
                new TypeReference<>() {} );
    }

    @Override
    public T create(T item) {
        items.add(item);
        return item;
    }

    @Override
    public T delete(T item) {
        T oldItem = null;
        for(T object: items) {
            if(item.getId() == object.getId()) {
                oldItem = object;
            }
        }
        items.remove(oldItem);
        return item;
    }

    @Override
    public T update(T item) {
        T oldItem = null;
        for(T object: items) {
            if(item.getId() == object.getId()) {
                oldItem = object;
            }
        }
        items.set(items.indexOf(oldItem), item);
        return item;
    }

    @Override
    public T getById(Integer id) {
        for(T item: items) {
            if(id.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    public void export() throws IOException {
        mapper.writerFor(new TypeReference<List<T>> () { }).writeValue(path.toFile(), items);
    }
}
