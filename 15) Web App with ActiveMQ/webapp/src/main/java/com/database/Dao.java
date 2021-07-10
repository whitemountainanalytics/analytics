package com.database;

import com.domain.Registrant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {

    private static JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Dao.class);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        Dao.jdbcTemplate = jdbcTemplate;
    }

    public void saveRegistration(Registrant registrant, String date) {

        String query = "Insert into registration" +
                "(fname, lname, email, phone, addr_line1, city, state, zip, class_name, create_date, active) values ('"
                + registrant.getFname() + "','" + registrant.getLname() + "','" + registrant.getEmail() + "','" + registrant.getPhone()
                + "','" + registrant.getAddr_line1() + "','" +  registrant.getCity() + "','" +  registrant.getState() + "','" +  registrant.getZip()
                + "','" +  registrant.getClass_name() + "','" + date + "','1')";

        jdbcTemplate.execute(query);
        logger.info("Inserted record into registration table.");
    }

}
