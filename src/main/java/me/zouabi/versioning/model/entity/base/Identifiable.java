package me.zouabi.versioning.model.entity.base;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {

    ID getId();

    void setId(ID id);

}
