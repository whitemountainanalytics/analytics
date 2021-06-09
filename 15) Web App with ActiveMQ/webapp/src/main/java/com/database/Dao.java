package com.database;

import com.domain.AnalyticsPage;
import com.domain.Registrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {

    private static JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public Dao() {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {}

    public void saveRegistration(Registrant registrant, String date) {

        String query = "Insert into registration" +
                "(fname, lname, email, phone, addr_line1, city, state, zip, class_name, create_date, active) values ('"
                + registrant.getFname() + "','" + registrant.getLname() + "','" + registrant.getEmail() + "','" + registrant.getPhone()
                + "','" + registrant.getAddr_line1() + "','" +  registrant.getCity() + "','" +  registrant.getState() + "','" +  registrant.getZip()
                + "','" +  registrant.getClass_name() + "','" + date + "','1')";

        jdbcTemplate.execute(query);
    }

    public void savePageView(AnalyticsPage analyticsPage, String date) {

        String query = "Insert into page_viewed" +
                "(page_id, page_view_date) values ('"
                + analyticsPage.getPageID() + "','"  + date + "')";

        jdbcTemplate.execute(query);
    }
}
