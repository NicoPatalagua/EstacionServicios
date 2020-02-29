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

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle {
    private String direccion;
    private int id;
    private int tipo;
    private int cantidadSolicitada;
    private int montoConsignado;

    public Vehicle() {
    }

    public Vehicle(String direccion, int id, int tipo, int cantidadSolicitada, int montoConsignado) {
        this.direccion = direccion;
        this.id = id;
        this.tipo = tipo;
        this.cantidadSolicitada = cantidadSolicitada;
        this.montoConsignado = montoConsignado;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public int getMontoConsignado() {
        return montoConsignado;
    }

    public void setMontoConsignado(int montoConsignado) {
        this.montoConsignado = montoConsignado;
    }
}
