package org.spring.mvc.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@XStreamAlias("user")
public class User implements Serializable{
    private static final long serialVersionUID = -2780580321897392106L;

    @XStreamAlias("id")
    @XStreamAsAttribute
    private String userId;

    @XStreamAsAttribute
    @Pattern(regexp="w{4,30}")
    private String userName;

    @XStreamAsAttribute
    @Pattern(regexp="S{6,30}")
    private String password;

    @XStreamAsAttribute
    @Length(min=2,max=100)
    private String realName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @DecimalMin(value = "1000.00")
    @DecimalMax(value="100000.00")
    @NumberFormat(pattern = "#,###.##")
    private long salary;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
    }
}