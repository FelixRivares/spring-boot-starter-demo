package ru.sample;

import lombok.SneakyThrows;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();
    File baseDir = new File(System.getProperty("java.io.tmpdir"));
    Context ctx = tomcat.addContext("", baseDir.getAbsolutePath());
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AppConfig.class);
    DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
    Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet);
    ctx.addServletMappingDecoded("/*", "dispatcher");
    appContext.setServletContext(ctx.getServletContext());
    appContext.refresh();
    tomcat.start();
    tomcat.getServer().await();
  }

}

