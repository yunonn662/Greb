/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package greb_app;


import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Yun Onn
 */
public class Time {
    public static Time time = new Time(); //This time instance is declared to let other classes to access
    public static String current; //set as public static instance for access from other packages
    int currentHour=getRealHour(), currentMinute=getRealMinute();
    String currentTime;
    public static String CLT ; //An instance for keeping Customer List Last Updated Time
    public static String DLT ; //An instance for keeping Driver List Last Updated Time
    

    Timer t = new Timer();
    
    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }
    
    //Method to get only real time hour.
    public int getRealHour(){ 
        LocalTime localTime = LocalTime.now();
        int hour= localTime.get(ChronoField.HOUR_OF_DAY);
        return hour;
    }
    
    //Method to get only real time minute.
    public int getRealMinute(){
        LocalTime localTime = LocalTime.now();
        int minute= localTime.get(ChronoField.MINUTE_OF_DAY);
        
        return minute%60;
    }
    
    //Method to manipulate real time (addition of time), and also get current time in 24 hour format.
    public String getCurrentTime(int plusMinute){
        
        String option ="";
        if(plusMinute==0) //Argument 0 means to get current time
            
            option = "A";
        if(plusMinute==1)
            option = "B"; //Argument 1 means to add 1 minute to current time
                          //Helps simulate 1 minute = 1 second environment.
        if(plusMinute>1)
            option = "C"; //Argument >1 means to add >1 minute to current time to 
                          //get Estimate Arrival Time
        switch(option){
            case "A": 
                if(currentHour<10){
                    currentTime = "0" + currentHour;
                }else{
                    currentTime = String.valueOf(currentHour);
                }
                
                if(currentMinute<10){
                    currentTime+="0"+currentMinute;
                }else{
                    currentTime+=currentMinute;
                }
        
                return currentTime;
                
            case "B":
                if(currentMinute<60){
                    currentMinute+=1;
                }
                
                if (currentMinute>59){
                    currentMinute=currentMinute-60;
                    currentHour+=1;
                }
                
                if(currentHour>23){
                    currentHour = currentHour - 24;
                } 
                
                if(currentHour<10){
                    currentTime = "0" + currentHour;
                }else{
                    currentTime = String.valueOf(currentHour);
                }
                
                if(currentMinute<10){
                    currentTime+="0"+currentMinute;
                }else{
                    currentTime+=currentMinute;
                }
        
                return currentTime;
                
            case "C":
                int hour = plusMinute/60;
                int minute = plusMinute%60;
                currentMinute += minute;
                currentHour += hour;
                if(currentMinute>59){
                    int count = currentMinute/60;
                    currentMinute = currentMinute - (count*60);
                    currentHour += count;
                }
                if(currentHour>23){
                    int count = currentHour/24;
                    currentHour = currentHour - (count*24);
                }    
                
                if(currentHour<10){
                    currentTime = "0" + currentHour;
                }else{
                    currentTime = String.valueOf(currentHour);
                }
                
                if(currentMinute<10){
                    currentTime+="0"+currentMinute;
                }else{
                    currentTime+=currentMinute;
                }
                
                return currentTime;
        }
        return currentTime;
    }
    
    //Method to start 1 hour = 1 minute, 1 minute = 1 second environment time
    //It will constantly update current variable, so we can access present time under this environment
    public void start(){ 
        Time x= new Time();
        t.schedule(new TimerTask() {
        
            @Override
            public void run() {
                current=x.getCurrentTime(1);

            }
        }, 0, 1000);
    }
    
    //Method to stop the environment time.
    public void stop(){
        t.cancel();
    }
    
    
    //Method to calculate Estimated Arrival Time using processed duration and add on current time.
    public String calculateETA(String time,int duration){
        String eta;
        int hour = Integer.parseInt(time.substring(0,2));
        int min = Integer.parseInt(time.substring(2,4));
        int addHour = duration/60;
        int addMinute = duration%60;
                min += addMinute;
                hour += addHour;
                if(min>59){
                    int count = min/60;
                    min = min - (count*60);
                    hour += count;
                }
                if(hour>23){
                    int count = hour/24;
                    hour = hour - (count*24);
                }    
                
                if(hour<10){
                    eta = "0" + hour;
                }else{
                    eta = String.valueOf(hour);
                }
                
                if(min<10){
                    eta+="0"+min;
                }else{
                    eta+=min;
                }
                return eta;
    }

}//End of Time Class
