package greb_app;

import GUI.HomePage;
import static greb_app.Time.time; //This is crucial to sync the whole system, it must be imported into all classes that uses time


public class Greb_App{

    public static void main(String[] args){
        time.start(); //Start the environment time
        HomePage home = new HomePage(); //Run the HomePage, start the application
        home.setVisible(true);
        
    }
 
}
