/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willi
 */

@XmlRootElement(name = "bool")
public class boolean1 {
    private boolean value;

    public boolean1() {
    }

    public boolean1(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
}
