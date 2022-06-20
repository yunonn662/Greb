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
                        driver.UpdateDriverLocation(jdrivername, jcx, jcy); //Update the location of the driver to customer's current location
                        customer.setStatus_PickedUp(jname); //Updating the status of the customer to 'Picked Up'
                        Time.CLT = Time.current; //Update Last Updated Time of Customer Table
                        Time.DLT = Time.current; //Update Last Updated Time of Driver Table
                        JOptionPane.showMessageDialog(null,jname+" is picked up by "+jdrivername);
                        

                    }
                }, longDtoC*1000); 
            
            
            
                t2.schedule(new TimerTask() { //Second timer is to simulate Customer finishes the trip.
                    @Override
                    public void run() {
                        driver.UpdateDriverLocation(jdrivername, jdx, jdy); //Update the location of the driver to customer's destination
                        customer.UpdateCustomerLocation(jname, jdx, jdy); //Update the location of the customer to their destination
                        customer.setStatus_Reached(jname);  //Updating the status of the customer to 'Reached'
                        driver.removePassenger(jdrivername);  //Removing the passenger of the driver upon completing the trip
                        driver.setStatus_Available(jdrivername); //Updating the status of the driver to 'Available'
                        Time.CLT = Time.current; //Update Last Updated Time of Customer Table
                        Time.DLT = Time.current; //Update Last Updated Time of Driver Table
                        JOptionPane.showMessageDialog(null,jname+" has reached his/her destination safely!");
                        FeedBack fb = new FeedBack(); 
                        fb.setVisible(true); //Show the FeedBack GUI


                    }
                }, (longDtoC+longCtoT)*1000);  
    }//End of startTrip method
    
}//End of Trip Class
