package org.spring.dao.jdbcHibernate;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("scoreService")
public class ScoreService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addScore(String userName,int toAdd){
        String sql = "UPDATE t_user u SET u.score = u.score + ? WHERE user_name = ?";
        jdbcTemplate.update(sql,toAdd,userName);
        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();

        System.out.println("[scoreService.addScore]激活连接数量:"+basicDataSource.getNumActive());
    }
}
