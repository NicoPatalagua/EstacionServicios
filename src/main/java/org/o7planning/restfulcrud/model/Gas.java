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

@XmlRootElement(name = "gas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gas {
    private int TipoGas;
    private int price;
    private int cantidadDisponible;

    public Gas() {
    }

    public Gas(int TipoGas, int price, int cantidadDisponible) {
        this.TipoGas = TipoGas;
        this.price = price;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getTipoGas() {
        return TipoGas;
    }

    public void setTipoGas(int TipoGas) {
        this.TipoGas = TipoGas;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    
}
