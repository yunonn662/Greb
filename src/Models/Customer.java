package Models;



/**
 *
 * @author Yun Onn
 */
public class Customer {
    private String name;

    private Integer expected_arrival_time;

    private Integer capacity;

    private Double curr_longitude;

    private Double curr_latitude;

    private Double dest_longitude;

    private Double dest_latitude;
    
    private String Status;

    public Customer(String name, Integer expected_arrival_time, Integer capacity, Double curr_longitude, Double curr_latitude, Double dest_longitude, Double dest_latitude, String status) {
        this.name = name;
        this.expected_arrival_time = expected_arrival_time;
        this.capacity = capacity;
        this.curr_longitude = curr_longitude;
        this.curr_latitude = curr_latitude;
        this.dest_longitude = dest_longitude;
        this.dest_latitude = dest_latitude;
        this.Status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public void setExpected_arrival_time(Integer expected_arrival_time) {
        this.expected_arrival_time = expected_arrival_time;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getCurr_longitude() {
        return curr_longitude;
    }

    public void setCurr_longitude(Double curr_longitude) {
        this.curr_longitude = curr_longitude;
    }

    public Double getCurr_latitude() {
        return curr_latitude;
    }

    public void setCurr_latitude(Double curr_latitude) {
        this.curr_latitude = curr_latitude;
    }

    public Double getDest_longitude() {
        return dest_longitude;
    }

    public void setDest_longitude(Double dest_longitude) {
        this.dest_longitude = dest_longitude;
    }

    public Double getDest_latitude() {
        return dest_latitude;
    }

    public void setDest_latitude(Double dest_latitude) {
        this.dest_latitude = dest_latitude;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}//End of Customer Class
