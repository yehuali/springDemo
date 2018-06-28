package org.spring.dao.jdbcHibernate;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//此版本不需要使用@Table标签
@Entity(name="t_user")
public class User implements Serializable {
    @Id
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="score")
    private int score;
    @Column(name="last_logon_time")
    private long lastLogonTime;


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


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public long getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(long lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }
}
