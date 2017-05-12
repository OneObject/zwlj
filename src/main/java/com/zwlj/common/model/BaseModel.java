package com.zwlj.common.model;

import java.io.Serializable;

public abstract class BaseModel<ID extends Serializable> implements Serializable, Cloneable {

    public abstract ID getId();

}
