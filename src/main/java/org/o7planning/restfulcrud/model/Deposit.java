/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willi
 */

@XmlRootElement(name = "deposit")
@XmlAccessorType(XmlAccessType.FIELD)
public class Deposit {
   private int idEstacion;
   private int idCarro;
   private int fuel;
   private int cantidad;
   private int valor;

    public Deposit() {
    }

    public Deposit(int idEstacion, int idCarro, int fuel, int cantidad, int valor) {
        this.idEstacion = idEstacion;
        this.idCarro = idCarro;
        this.fuel = fuel;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
   
   
}
