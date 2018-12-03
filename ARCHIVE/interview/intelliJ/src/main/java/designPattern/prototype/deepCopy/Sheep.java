package designPattern.prototype.deepCopy;

import java.io.Serializable;
import java.util.Date;


public class Sheep implements Cloneable,Serializable {
    private String name;
    private Date birth;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();  //直接调用object对象的clone()方法！


        //添加如下代码实现深复制(deep Clone)
        Sheep s = (Sheep) obj;
        //Object s1 = (Object) s;
        s.birth = (Date) this.birth.clone();  //把属性也进行克隆！
        return obj;
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
