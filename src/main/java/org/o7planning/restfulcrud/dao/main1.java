/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.o7planning.restfulcrud.model.Deposit;
import org.o7planning.restfulcrud.model.Gas;
import org.o7planning.restfulcrud.model.Station;

/**
 *
 * @author willi
 */
public class main1 {

    EstacionDAO cs = new EstacionDAO();
    VehiculoDAO cv = new VehiculoDAO();

    public void registrarEstacion() {
        try {
            Station e = new Station();
            e.setId(2);
            e.setDeposit(20);
            Gas fuel = new Gas();
            fuel.setTipoGas(0);
            fuel.setPrice(4);
            fuel.setCantidadDisponible(400);
            e.AddFuel(fuel);
            Gas fuel2 = new Gas();
            fuel2.setTipoGas(1);
            fuel2.setPrice(3);
            fuel2.setCantidadDisponible(500);
            e.AddFuel(fuel2);
            cs.RegistrarEstacion(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void validarDeposito() {
        try {
            boolean e2 = cs.ValidateDeposit(2, 1);
            System.out.println("Validar Deposito: " + e2);
        } catch (Exception ex) {
            Logger.getLogger(main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RegistrarPago() {
        try {
            boolean e2 = cs.RegistrarPago(new Deposit(2, 1, 0, 4,0));
            System.out.println("Realizar Cobro: " + e2);
        } catch (Exception ex) {
            Logger.getLogger(main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //vehiculo
    public void consultarEstacion() {

        try {
            Station e2 = cs.getEstacion(2);

            System.out.println("Resultado: ");
            System.out.println(e2.getId());
            System.out.println(e2.getDeposit());
            System.out.println(e2.getFuels().size());
            for (int i = 0; i < e2.getFuels().size(); i++) {
                System.out.println("- " + e2.getFuels().get(i).getTipoGas());
                System.out.println(" " + e2.getFuels().get(i).getPrice());
                System.out.println(" " + e2.getFuels().get(i).getCantidadDisponible());
            }
        } catch (Exception ex) {
            Logger.getLogger(main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SendDeposit() {
        try {
            boolean e2 = cv.sendDeposit(new Deposit(2, 1, 0, 4,20));
            System.out.println("send Deposito: " + e2);
        } catch (Exception ex) {
            Logger.getLogger(main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        main1 m = new main1();
        
        //m.registrarEstacion();
        m.consultarEstacion();
        //m.SendDeposit();
        //m.validarDeposito();
        m.RegistrarPago();
    }
}
