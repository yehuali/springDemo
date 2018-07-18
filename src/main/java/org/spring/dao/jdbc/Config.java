package org.spring.dao.jdbc;

import java.io.Serializable;

public class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jdbcDriverClassName;
    private String jdbcUrl;
    private String jdbcUserName;
    private String jdbcPassword;

    public Config() {
    }

    public Config(String jdbcDriverClassName, String jdbcUrl, String jdbcUserName, String jdbcPassword) {
        this.jdbcDriverClassName = jdbcDriverClassName;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUserName = jdbcUserName;
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }
}
