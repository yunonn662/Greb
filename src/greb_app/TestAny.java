/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package greb_app;

import DatabaseConnection.CustomerManager;
import DatabaseConnection.DriverManager;




/**
 *
 * @author Yun Onn
 */
public class TestAny {
    static CustomerManager customer = new CustomerManager();
    static DriverManager driver = new DriverManager();
    static Time time = new Time();
    public static void main(String[] args){
        System.out.println(time.calculateETA(time.getCurrentTime(0), 197));

    }

}
