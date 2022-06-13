/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package greb_app;

import DatabaseConnection.CustomerManager;
import DatabaseConnection.DBConnect;
import DatabaseConnection.DriverManager;
import Map.Calculations;
import Map.Graph;
import Models.Driver;
import static greb_app.Time.time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Yun Onn
 */
public class Book {
    Connection con = null;

    
    public int DtoC(Double C_X, Double C_Y, Double D_X, Double D_Y){ //Calculate the time travel needed for driver to reach customer
        double DtoC = Calculations.calculateDistance(C_X, C_Y, D_X, D_Y); //Calculate distance between Driver and Customer
        double duration_DtoC = Calculations.calculateDuration(50, DtoC); //We assume the average speed for all drivers is 50km/h
        int duration = (int)duration_DtoC;
        return duration;
    }
    
    public int CtoT(Double C_X, Double C_Y, Double T_X, Double T_Y){ //Method to calculate the time travel needed for customer to reach destination
        double CtoT = Calculations.calculateDistance(C_X, C_Y, T_X, T_Y); //Calculate distance between Customer and Target
        double duration_CtoT = Calculations.calculateDuration(50, CtoT); //We assume the average speed for all drivers is 50km/h
        int duration = (int)duration_CtoT;
        return duration;
    }
    
    
    public boolean UpdateDriverEAT(Graph graph, String name) throws SQLException{ //Method to update estimated time arrival 
        DriverManager driver = new DriverManager();                               //of all driver based on specific customer.
        CustomerManager customer = new CustomerManager();
        
        String customerName = customer.getCustomer(name).getName(); //Customer name

        String destinationName = customer.getLocation(name).getName();// Destination name
        
        int CtoT = (int) graph.getEdgeWeight(customerName, destinationName); //Time travel from customer to destination
        
        for(int i=0; i<driver.getAllDriver().size(); i++){ 
            Driver driverName = (Driver) driver.getAllDriver().get(i);
            int DtoC = (int)graph.getEdgeWeight(driverName.getName(), customerName); //Time travel from driver to customer
            int totalDuration = CtoT + DtoC; //Total Time Travel
            int EAT = Integer.parseInt(time.calculateETA(Time.current, totalDuration)); //Adding TotalTimeTravel to current time
            
            try{ //Connect to database to update.
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set eat='"+EAT+"'where name='"+driverName.getName()+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return true;
}
    
    public Graph CustomerGraph(String name) throws SQLException{ //Method to create a graph for specific customer.
        Graph<String,Integer> graph = new Graph<>();
        DriverManager driver = new DriverManager();
        CustomerManager customer = new CustomerManager();
        double C_X = customer.getCustomer(name).getCurr_longitude(); //Customer Current Longitude
        double C_Y = customer.getCustomer(name).getCurr_latitude();  //Customer Current Latitude
        double T_X = customer.getCustomer(name).getDest_longitude(); //Customer Destination Longitude
        double T_Y = customer.getCustomer(name).getDest_latitude(); //Customer Destination Latitude
                
         
        
        for(int i=0; i<driver.getAllDriver().size(); i++){ //Adding all registered driver as Vertex.
            Driver driverName = (Driver) driver.getAllDriver().get(i);
            graph.addVertex(driverName.getName());
        }
        String customerName = customer.getCustomer(name).getName(); //Customer name
        
        String destinationName = customer.getLocation(name).getName();// Destination name
        
        graph.addVertex(customerName); //Adding the specific customer as Vertex.
        graph.addVertex(destinationName); //Adding the destination as Vertex.
        
        for(int i=0; i<driver.getAllDriver().size(); i++){ //Adding edges each for all driver to that specific customer
            Driver driverName = (Driver) driver.getAllDriver().get(i);
            graph.addEdge(driverName.getName(), customerName,
                    DtoC(C_X,C_Y,driverName.getLongitude(),driverName.getLatitude()));
        }
        
        graph.addEdge(customerName, destinationName, CtoT(C_X,C_Y,T_X,T_Y)); //Adding edge for specific customer to destination
        
        return graph;
    }
    
        
    }//End of Book Class
