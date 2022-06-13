package greb_app;

import DatabaseConnection.CustomerManager;
import DatabaseConnection.DriverManager;
import GUI.HomePage;
import static greb_app.Time.time; //This is crucial to sync the whole system, it must be imported into all classes that uses time


public class Greb_App{
    static CustomerManager customer = new CustomerManager();
    static DriverManager driver = new DriverManager();
    public static int count = 0;


    public static void main(String[] args){
        time.start(); //Start the environment time
//        time.CLT = time.current; //Set a default time for Customer Last Updated Time
//        time.DLT = time.current; //Set a default time for Driver Last Updated Time
        HomePage home = new HomePage(); //Run the HomePage, start the application
        home.setVisible(true);
        
    }
 
}
