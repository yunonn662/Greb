/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package greb_app;


import DatabaseConnection.CustomerManager;
import DatabaseConnection.DriverManager;
import GUI.FeedBack;
import Map.Graph;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Yun Onn
 */
public class Trip {
    
    CustomerManager customer = new CustomerManager();
    DriverManager driver = new DriverManager(); 
    Timer t1 = new Timer(); //Timer to simulate driver picking up customer.
    Timer t2 = new Timer(); //Timer to simulate customer reaching destination

    //Method to simulate trip, after ending the trip, it will call out FeedBack frame and prompt user to rate the driver.
    public void startTrip(String jname, String jdrivername, Double jcx, Double jcy, Double jdx, Double jdy, Graph graph) throws SQLException{
        String customerName = customer.getCustomer(jname).getName(); //Customer name
        String driverName = driver.getDriver(jdrivername).getName();// Driver name
        String destinationName = customer.getLocation(jname).getName();//Destination name
        int DtoC = (int) graph.getEdgeWeight(driverName,customerName); //Duration of Driver picking up Customer
        int CtoT = (int) graph.getEdgeWeight(customerName, destinationName); //Duration of Customer reaching Destination(Target)
        long longDtoC = DtoC; //Converting to long type for timer delay input.
        long longCtoT = CtoT;
        
        System.out.println(longDtoC);
        System.out.println(longCtoT);
        
                t1.schedule(new TimerTask() { //First timer is to simulate Driver picking up Customer.
                    @Override
                    public void run() {
                        driver.UpdateDriverLocation(jdrivername, jcx, jcy);
                        customer.setStatus_PickedUp(jname);
                        Time.CLT = Time.current;
                        Time.DLT = Time.current;
                        JOptionPane.showMessageDialog(null,jname+" is picked up by "+jdrivername);
                        

                    }
                }, longDtoC*1000); 
            
            
            
                t2.schedule(new TimerTask() { //Second timer is to simulate Customer finishes the trip.
                    @Override
                    public void run() {
                        driver.UpdateDriverLocation(jdrivername, jdx, jdy);
                        customer.UpdateCustomerLocation(jname, jdx, jdy);
                        customer.setStatus_Reached(jname);
                        driver.removePassenger(jdrivername);
                        driver.setStatus_Available(jdrivername);
                        Time.CLT = Time.current;
                        Time.DLT = Time.current;
                        JOptionPane.showMessageDialog(null,jname+" has reached his/her destination safely!");
                        FeedBack fb = new FeedBack();
                        fb.setVisible(true);


                    }
                }, (longDtoC+longCtoT)*1000);  
    }//End of startTrip method
    
}//End of Trip Class
