/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.model;

import java.util.Date;

/**
 *
 * @author chinh
 */
public class ElectricitywaterModel {
    private int id,old_electricity_reading,new_electricity_reading,old_water_reading,new_water_reading;
    private String room_id,user_id,status;
    private float electricity_price;
    private Date payment_time;

    public ElectricitywaterModel() {
    }

    public ElectricitywaterModel(int id, int old_electricity_reading, int new_electricity_reading, int old_water_reading, int new_water_reading, String room_id, String user_id, String status, float electricity_price, Date payment_time) {
        this.id = id;
        this.old_electricity_reading = old_electricity_reading;
        this.new_electricity_reading = new_electricity_reading;
        this.old_water_reading = old_water_reading;
        this.new_water_reading = new_water_reading;
        this.room_id = room_id;
        this.user_id = user_id;
        this.status = status;
        this.electricity_price = electricity_price;
        this.payment_time = payment_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOld_electricity_reading() {
        return old_electricity_reading;
    }

    public void setOld_electricity_reading(int old_electricity_reading) {
        this.old_electricity_reading = old_electricity_reading;
    }

    public int getNew_electricity_reading() {
        return new_electricity_reading;
    }

    public void setNew_electricity_reading(int new_electricity_reading) {
        this.new_electricity_reading = new_electricity_reading;
    }

    public int getOld_water_reading() {
        return old_water_reading;
    }

    public void setOld_water_reading(int old_water_reading) {
        this.old_water_reading = old_water_reading;
    }

    public int getNew_water_reading() {
        return new_water_reading;
    }

    public void setNew_water_reading(int new_water_reading) {
        this.new_water_reading = new_water_reading;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getElectricity_price() {
        return electricity_price;
    }

    public void setElectricity_price(float electricity_price) {
        this.electricity_price = electricity_price;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }
    
    
}
