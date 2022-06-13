package Models;

/**
 *
 * @author Yun Onn
 */
public class Driver {
    private String name;
    
    private Integer capacity;

    private Double longitude;

    private Double latitude;

    private Double rating;
    
    private String status;
    
    private Integer EAT;

    public Driver(String name, Integer capacity, Double longitude, Double latitude, Double rating, String status, Integer EAT) {
        this.name = name;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rating = rating;
        this.status = status;
        this.EAT = EAT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEAT() {
        return EAT;
    }

    public void setEAT(Integer EAT) {
        this.EAT = EAT;
    }
    
}//End of Driver Class
