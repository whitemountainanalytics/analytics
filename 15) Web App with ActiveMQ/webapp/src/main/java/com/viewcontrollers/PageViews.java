package com.viewcontrollers;

import com.cookie.CookieHandler;
import com.database.Dao;
import com.domain.AnalyticsPage;
import com.domain.AnalyticsRegistration;
import com.domain.Registrant;
import com.messagecontrol.ActiveMQhandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class PageViews {

    ActiveMQhandler activeMQhandler;
    private String remoteIP;
    private String userAgent;
    String sessionID;
    private static final Logger LOGGER = LoggerFactory.getLogger(PageViews.class);
    Cookie[] cookies;
    Cookie cookie = null;

    // used to create and test error conditions
    int a = 1;
    int b = 0;
    int c = 1;

    @GetMapping("/")
    public String index(Model model, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) {

        // manage session and cookies
        sessionID = session.getId();
        cookies = request.getCookies();
        CookieHandler cookieHandler = new CookieHandler();
        cookie = cookieHandler.setCookie(cookies);
        response.addCookie(cookie);

        // add this to the database and apache logs
        remoteIP = request.getRemoteAddr();
        userAgent = request.getHeader("User-Agent");

        String date = getDate();

        // dump pageview data to the message bus
        try {
            AnalyticsPage analyticsPage = new AnalyticsPage(sessionID, cookie.getValue(), date, "4", "greeting");

            // uncomment the statement below to create an exception
            // a=c/b;
            activeMQhandler = new ActiveMQhandler();
            activeMQhandler.sendMessage(analyticsPage, "pageview");
        } catch (Exception e ){
            LOGGER.error(remoteIP + " - " + "SessionID: " + sessionID
                    + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent + " - " + e.getMessage());
        }

        return "greeting";
    }

    @GetMapping("/register")
    public String showForm(Model model, HttpSession session, HttpServletRequest request,
    HttpServletResponse response) {

        // manage session and cookies
        sessionID = session.getId();
        cookies = request.getCookies();
        CookieHandler cookieHandler = new CookieHandler();
        cookie = cookieHandler.setCookie(cookies);
        response.addCookie(cookie);

        // add this to the database and apache logs
        remoteIP = request.getRemoteAddr();
        userAgent = request.getHeader("User-Agent");

        String date = getDate();

        // add registrant to model
        Registrant registrant = new Registrant();
        model.addAttribute("registrant", registrant);

        List<String> class_name = Arrays.asList("Yoga", "Cooking", "Auto Racing", "Piano");
        model.addAttribute("class_name", class_name);
        // LOGGER.info(remoteIP + " - " + "SessionID: " + sessionID + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent);

        // send pageview data to the message bus
        try {
            // uncomment the statement below to create an exception
            // a=c/b;
            AnalyticsPage analyticsPage = new AnalyticsPage(sessionID, cookie.getValue(), date, "5", "register");

            activeMQhandler = new ActiveMQhandler();
            activeMQhandler.sendMessage(analyticsPage, "pageview");
        } catch (Exception e ){LOGGER.error(remoteIP + " - " + "SessionID: " + sessionID
                + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent + " - " + e.getMessage());}

        return "register";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("registrant") Registrant registrant, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {

        // manage session and cookies
        sessionID = session.getId();
        cookies = request.getCookies();

        CookieHandler cookieHandler = new CookieHandler();
        cookie = cookieHandler.setCookie(cookies);
        response.addCookie(cookie);

        // add this to the database and apache logs
        remoteIP = request.getRemoteAddr();
        userAgent = request.getHeader("User-Agent");

        String date = getDate();

        // send pageview data to the message bus
        try {
            AnalyticsPage analyticsPage = new AnalyticsPage(sessionID, cookie.getValue(), date, "6", "register_success");

            activeMQhandler = new ActiveMQhandler();
            activeMQhandler.sendMessage(analyticsPage, "pageview");

            // uncomment the statement below to create an exception
            // a=c/b;
        } catch (Exception e ){LOGGER.error(remoteIP + " - " + "SessionID: " + sessionID
                + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent + " - " + e.getMessage());}

        // log the registration to the databse including PI data
        try {
            Dao dao = new Dao();
            registrant.setRegistration_date(date.toString());
            dao.saveRegistration(registrant, date);

            // uncomment the statement below to create an exception
           // a=c/b;
        } catch (Exception e ){LOGGER.error(remoteIP + " - " + "SessionID: " + sessionID
                + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent + " - " + e.getMessage()); return "error";}

        // send non pi registrant data to the message bus
        try {
            AnalyticsRegistration analyticsRegistration
                    = new AnalyticsRegistration(sessionID, cookie.getValue(),
                    registrant.getRegistration_date(), registrant.getFname(), registrant.getZip(), registrant.getClass_name());
            activeMQhandler = new ActiveMQhandler();
            activeMQhandler.sendMessage(analyticsRegistration, "registrant");
        } catch (Exception e ){LOGGER.error(remoteIP + " - " + "SessionID: " + sessionID
                + " - Cookie:" + cookie + " - Page: register" + " - " + userAgent + " - " + e.getMessage());}

        // LOGGER.info(remoteIP + " - " + "SessionID: " + sessionID + " - Cookie:" + cookie + " - Page: register_success" + " - " + userAgent);
        return "register_success";
    }

    private String getDate(){
        // ensure both the db record and the message have the same date
        // format the date correctly for the database
        String datepattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datepattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

}

