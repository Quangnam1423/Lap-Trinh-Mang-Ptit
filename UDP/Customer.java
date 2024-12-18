package UDP;

import java.util.*;
import java.io.*;

public class Customer implements Serializable
{
    private String id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(String id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", code=" + code + ", name=" + name + ", dayOfBirth=" + dayOfBirth + ", userName="
                + userName + "]";
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    private static final long serialVersionUID = 20151107;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}