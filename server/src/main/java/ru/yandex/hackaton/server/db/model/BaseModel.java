package ru.yandex.hackaton.server.db.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Sergey Polovko
 */
@MappedSuperclass
public abstract class BaseModel<ID extends Serializable> {

    public abstract ID getId();
    public abstract void setId(ID id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel baseModel = (BaseModel) o;
        return !(getId() != null ? !getId().equals(baseModel.getId()) : baseModel.getId() != null);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
}
