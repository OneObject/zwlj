package com.zwlj.app.boke.model;

import com.zwlj.common.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "base_user")
public class UserEntity extends BaseModel<Long> {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "identity")
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String userName;

    private long bathDate;

    private int sex;

    private String address;

    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getBathDate() {
        return bathDate;
    }

    public void setBathDate(long bathDate) {
        this.bathDate = bathDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bathDate=" + bathDate +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
