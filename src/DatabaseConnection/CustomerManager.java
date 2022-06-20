package DatabaseConnection;

import Models.Customer;
import greb_app.Location;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

public class CustomerManager
{
    Connection con;
    
    public CustomerManager() {
        this.con = null;
    }
    
    //Method to add customer to the database
    public void addCustomer(String name,Integer ETA,Integer capacity, Double current_X, Double current_Y, Double dest_X, Double dest_Y) {
        try {
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "insert into customer values(?,?,?,?,?,?,?,'Pending')";
            
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.setString(1, name);
            ps.setInt(2, ETA);
            ps.setInt(3, capacity);
            ps.setDouble(4, current_X);
            ps.setDouble(5, current_Y);
            ps.setDouble(6, dest_X);
            ps.setDouble(7, dest_Y);
            ps.executeUpdate();
            System.out.println("Customer added successfully.");
            con.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to removeCustomer from the database
    public void removeCustomer(String name) {
        try {
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Delete from customer where name='"+name+"'";
            
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer removed successfully.");
            con.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to update customer details in the database
    public void updateCustomer(String name,Integer ETA,Integer capacity, Double current_X, Double current_Y, Double dest_X, Double dest_Y) {
        try {
            DBConnect db = new DBConnect();
            this.con = db.connect();
            String query = "Update customer set eta='"+ETA+"',"
                    +"capacity='" + capacity +"',"+"current_X='" + current_X + "'," +
                    "current_Y='" + current_Y + "'," + "dest_X='" + dest_X + "'," + 
                    "dest_Y='" + dest_Y + "' where name='"+name+"'";
            
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer updated successfully.");
            con.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to getCustomer details and return a Customer object for easy access of specific details
    public Customer getCustomer(String name) throws SQLException{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Select * from customer where name=?";
            
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("name"),rs.getInt("eta"),rs.getInt("capacity"),rs.getDouble("current_X"),
                        rs.getDouble("current_Y"),rs.getDouble("dest_X"),rs.getDouble("dest_Y"),rs.getString("status"));
            }
            else {
                con.close();
                throw new IllegalArgumentException("User not found");
            }
    }
    
    //Method to get location of customer, but all location is just named "destination" because we didn't store any location name.
    //As we assume location name isn't neccessary in this project, and also increase complexity of the project.
    public Location getLocation(String name) throws SQLException{
        DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Select * from customer where name=?";
            
            PreparedStatement ps = this.con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //con.close();
                return new Location("destination",rs.getDouble("dest_X"),rs.getDouble("dest_Y"));
            }
            else {
                con.close();
                throw new IllegalArgumentException("User not found");
            }
    }
    
    //Method to set status of customer to 'Waiting' in the database
    public void setStatus_Waiting(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update customer set status = 'Waiting' where name='"+name+"'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer status updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to set status of customer to 'Pending' in the database
    public void setStatus_Pending(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update customer set status = 'Pending' where name='"+name+"'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer status updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to set status of customer to 'Picked Up' in the database
    public void setStatus_PickedUp(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update customer set status = 'Picked Up' where name='"+name+"'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer status updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to set status of customer to 'Reached' in the database
    public void setStatus_Reached(String name){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update customer set status = 'Reached' where name='"+name+"'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Customer status updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to update only customer location after successfully reaching their destination
    public void UpdateCustomerLocation(String name,Double x, Double y){
        try{
            DBConnect db = new DBConnect();
            con = db.connect();
            String query = "Update customer set current_X ='" + x + "',"+
                    "current_Y='" + y + "'where name='" + name + "'";
            
            PreparedStatement ps = con.prepareStatement(query); //PreparedStatement is a pre-compiled SQL statement to execute a parameterized query.
            ps.executeUpdate();
            System.out.println("Driver Location updated successfully.");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}//End of CustomerManager Class
