/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willi
 */

@XmlRootElement(name = "station")
@XmlAccessorType(XmlAccessType.FIELD)
public class Station{
    String direccion;
    int id;
    int Deposit;
    List<Gas> fuels; //combustibles ofrecidos en la estacion
    List<Vehicle> vehicles; //estaciones

    public Station() {
        direccion="";
        id=0;
        Deposit=0;
        fuels=new ArrayList<>();
        vehicles=new ArrayList<>();
    }

    public Station(String direccion, int id, int Deposit, List<Gas> fuels, List<Vehicle> vehicles) {
        this.direccion = direccion;
        this.id = id;
        this.Deposit = Deposit;
        this.fuels = fuels;
        this.vehicles = vehicles;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeposit() {
        return Deposit;
    }

    public void setDeposit(int Deposit) {
        this.Deposit = Deposit;
    }

    public List<Gas> getFuels() {
        return fuels;
    }

    public void setFuels(List<Gas> fuels) {
        this.fuels = fuels;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    
    public void AddFuel(Gas fuel){
        this.fuels.add(fuel);
    }
    public void AddVehicles(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }
    
    public void toString1(){
        System.out.println("estacion");
        System.out.println("id:"+this.id);
        System.out.println("address:"+this.direccion);
        System.out.println("dep:"+this.Deposit);
        System.out.println("fuels:"+this.fuels.size());
        System.out.println("ve:"+this.vehicles.size());
    }
}
