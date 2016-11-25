package edu.sjtu.core.resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener; 
 

public class SaveListener implements ServletContextListener {  
    public void contextDestroyed(ServletContextEvent e) {   
    } 
 
    public void contextInitialized(ServletContextEvent e) {  
    	System.out.println("scoresave thread start.");
        new Thread(new ScoreSaveRunnable()).start();
 
    } 
} 