/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.dao;

import java.math.BigInteger;
import org.o7planning.restfulcrud.model.Deposit;
import org.o7planning.restfulcrud.model.Gas;
import org.o7planning.restfulcrud.model.Station;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.sample.GasStation;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/**
 *
 * @author willi
 */
public class VehiculoDAO {

    private Web3j web3j;
    private Credentials credentials;
    private GasStation contrato;

    public VehiculoDAO() {
        this.web3j = Web3j.build(new HttpService("http://localhost:7545"));
        this.credentials = Credentials.create("066f0fc2aff41802d2420a6f5c93de9b8f6170bbadb35e5b3fed629702bcd4dd");//clave privada CUENTA VEHICULO
        try {
            this.contrato = GasStation.load("0x6a0EDC59f04d6B5849f572514f2d71b7Fea861e7", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sendDeposit(uint idStation, uint idVehiculo, TipoGas tipo,uint cantidadSolicitada )
    public boolean sendDeposit(Deposit deposito) {
        BigInteger idStation = new BigInteger(""+deposito.getIdEstacion());
        BigInteger idCar = new BigInteger("" + deposito.getIdCarro());
        BigInteger idFuel = new BigInteger("" + deposito.getFuel());
        BigInteger cantidad = new BigInteger("" + deposito.getCantidad());
        BigInteger value = new BigInteger("" + deposito.getValor());
        BigInteger val3 = new BigInteger("1000000000000000000");
        value = value.multiply(val3);
        TransactionReceipt resultado = null;
        try {
            System.out.println("entro");
            resultado = this.contrato.sendDeposit(idStation, idCar, idFuel, cantidad, value).send();
            System.out.println("salio");
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public Station getEstacion(int id) throws Exception {
        BigInteger idStation = new BigInteger("" + id);
        
        Tuple2 resultado = null;
        try {
            resultado = this.contrato.getStationGasInfo(idStation).send();
            BigInteger val3 = new BigInteger("1000000000000000000");
            System.out.println(resultado);
            Station estacion = new Station();
            estacion.setId(id);
            BigInteger val1=(BigInteger) resultado.getValue1();
            val1=val1.divide(val3);
            estacion.setDeposit(val1.intValue());
            BigInteger val2=(BigInteger) resultado.getValue2();
            int lengthFuels = val2.intValue();
            for (int i = 0; i < lengthFuels; i++) {
                 BigInteger fuel = new BigInteger("" +i);
                Tuple3 InfoGas= this.contrato.getGasInfo(idStation,fuel).send();
                BigInteger tipo=(BigInteger) InfoGas.getValue1();
                BigInteger precio=(BigInteger) InfoGas.getValue2();
                precio=precio.divide(val3);
                BigInteger cantidad=(BigInteger) InfoGas.getValue3();
                Gas gas=new Gas(tipo.intValue(), precio.intValue(), cantidad.intValue());
                estacion.AddFuel(gas);
            }
            return estacion;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
