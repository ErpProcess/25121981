package com.googlecode.exttag.tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.exttag.Configuration;

public class TagInitializeListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        Configuration.init(event.getServletContext().getInitParameter("ext-home"), event
                .getServletContext().getInitParameter("ext-styles"), event.getServletContext()
                .getInitParameter("ext-scripts"));
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

}
