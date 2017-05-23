package com.zwlj.app.life.model;

import com.zwlj.common.model.BaseModel;
import com.zwlj.common.utils.HibernateStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "life_resource")
public class ResourceEntity extends BaseModel<String> {

    @Id
    @GenericGenerator(name = "idGen", strategy = HibernateStrategy.UUID)
    @GeneratedValue(generator = "idGen")
    @Column(length = 40)
    private String id;

    public String getId() {
        return null;
    }
}
