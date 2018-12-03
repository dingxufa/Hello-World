package designPattern.prototype.shallowCopy;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ding on 2018/3/7.
 */
public class Sheep implements Cloneable,Serializable {
    private String name;
    private Date birth;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //直接调用object对象的clone()方法！
    }

    public Sheep(){}
    public Sheep(String name, Date birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
