    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.dao;

import com.sun.org.apache.xerces.internal.impl.dv.xs.IDREFDV;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.o7planning.restfulcrud.model.Deposit;
import org.o7planning.restfulcrud.model.Gas;
import org.o7planning.restfulcrud.model.Station;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.sample.GasStation;
import org.web3j.tuples.generated.Tuple1;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/**
 *
 * @author willi
 */
public class EstacionDAO {

    private Web3j web3j;
    private Credentials credentials;
    private GasStation contrato;

    public EstacionDAO() {
        this.web3j = Web3j.build(new HttpService("http://localhost:7545"));
        this.credentials = Credentials.create("cefd73b56cf1bde88f2c8a8e3ebd6fae86f72d063db87e93cde91f4ef252db9e");//clave privada CUENTA ESTACION
        try {                                
            this.contrato = GasStation.load("0x6a0EDC59f04d6B5849f572514f2d71b7Fea861e7", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean RegistrarEstacion(Station estacion) {
        BigInteger id = new BigInteger("" + estacion.getId());
        BigInteger deposito = new BigInteger("" + estacion.getDeposit());

        TransactionReceipt resultado = null;
        try {
            System.out.println("entro");
            resultado = this.contrato.setStation(id, deposito).send();
            for (int i = 0; i < estacion.getFuels().size(); i++) {
                RegistrarFuel(estacion.getId(), estacion.getFuels().get(i));
            }
            System.out.println("salio");
            return true;
        } catch (Exception ex) {
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
    
    public boolean RegistrarFuel(int id,Gas fuel) {
        BigInteger idStation = new BigInteger("" + id);
        BigInteger tipo = new BigInteger("" + fuel.getTipoGas());
        BigInteger precio = new BigInteger("" + fuel.getPrice());
        BigInteger cantidad = new BigInteger("" + fuel.getCantidadDisponible());
        TransactionReceipt resultado = null;
        try {
            System.out.println("entro");
            resultado = this.contrato.setStationFuel(idStation,tipo,precio,cantidad).send();
            System.out.println("salio");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean ValidateDeposit(int idstation,int idcar) throws Exception {
        BigInteger idStation = new BigInteger("" + idstation);
        BigInteger idCar = new BigInteger("" + idcar);
        
        boolean resultado = false;
        try {
            resultado = this.contrato.verificarDeposit(idStation,idCar).send();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultado;
    }
    //sendFuel(uint _station,uint Vehiculo, uint fuel, uint cantidadSolicitada) 
    public boolean RegistrarPago(Deposit deposito) {
        BigInteger idStation = new BigInteger("" + deposito.getIdEstacion());
        BigInteger idCar = new BigInteger("" + deposito.getIdCarro());
        BigInteger idFuel = new BigInteger("" + deposito.getFuel());
        BigInteger cantidad = new BigInteger("" + deposito.getCantidad());
        
        TransactionReceipt resultado = null;
        try {
            System.out.println("entro");
            resultado = this.contrato.sendFuel(idStation,idCar,idFuel,cantidad).send();
            System.out.println("salio");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
}
