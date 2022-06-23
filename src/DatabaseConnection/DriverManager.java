package DatabaseConnection;

import Models.Driver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DriverManager {
    Connection con = null;
    public static int count; //count is used to store the number of driver which is later used for the combobox in CustomerView
    
    //Method to add driver to the database
    public void addDriver(String name, Integer capacity, Double longitude, Double latitude){

        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "insert into driver values(?,?,?,?,5,'Available',null,null)"; //We set all driver's rating as 5 by default.
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.setString(1, name);
            ps.setInt(2, capacity);
            ps.setDouble(3, longitude);
            ps.setDouble(4, latitude);
            ps.executeUpdate();
            System.out.println("Driver added successfully.");
            JOptionPane.showMessageDialog(null,"Driver Registered!");
        } catch(SQLIntegrityConstraintViolationException e){
                JOptionPane.showMessageDialog(null, "Driver ID Already Registered!");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to remove driver from the database
    public void removeDriver(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Delete from driver where name='"+name+"'";
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();

            System.out.println("Driver removed successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to update driver details in the database
    public void updateDriver(String name, Integer capacity,Double longitude,Double latitude, Double rating){
         try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set capacity='" + capacity +"'," +"longitude ='"+longitude+"',"+
                    "latitude='"+latitude+"'"+ "rating='" + rating + "'where name='"+name+"'";
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to get driver datails from the database and return a Driver object for easier access of specific data.
    public Driver getDriver(String name) throws SQLException{
        DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Select * from driver where name=?";
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //con.close();
                return new Driver(rs.getString("name"),rs.getInt("capacity"),rs.getDouble("longitude"),
                        rs.getDouble("latitude"),rs.getDouble("rating"),rs.getString("status"),rs.getInt("eat"));
            }
            else {
                con.close();
                throw new IllegalArgumentException("Driver not found.");
            }
    }
    
    //Method to set driver rating in the database
    public void setRating(String name,double rating){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set rating='" + rating + "' where name ='" + name + "'";
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver rating updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to get all driver's details from the database and returns an 
    //ArrayList of Driver objects for UpdateDriverEAT method in Book class.
    public List getAllDriver(){
        List<Driver> driverNames = new ArrayList<>();
        try {
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "select * from driver";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                driverNames.add(new Driver(rs.getString("name"),rs.getInt("capacity"),rs.getDouble("longitude"),
                        rs.getDouble("latitude"),rs.getDouble("rating"),rs.getString("status"),rs.getInt("eat")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        count = driverNames.size();
        return driverNames;
    }
    
    //Method to set Driver's Customer after customer booked a ride.
    public void setPassenger(String name, String customerName){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set passenger = '"+customerName+"' where name='" + name + "'" ;
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver's customer updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Method to set driver status to Unavailable
    public void setStatus_Unavailable(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set status = 'Not Available' where name='" + name + "'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver status updated to 'Unavailable' successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //Method to set driver status to Available
    public void setStatus_Available(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set status = 'Available' where name='"+name+"'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver status updated to 'Available' successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //Method to update only driver location after successfully fetching thier customer to destination
    public void UpdateDriverLocation(String name,Double x, Double y){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set longitude ='" + x + "',"+
                    "latitude='" + y + "'where name='" + name + "'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver Location updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to remove passenger after Driver successfully complete the trip.
    public void removePassenger(String drivername){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update driver set passenger = null where name='" + drivername + "'" ;
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver's customer updated to 'NULL' successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}//End of DriverManager Class
