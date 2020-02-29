/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import javax.ws.rs.Path;

import java.io.IOException;
import java.math.BigInteger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.o7planning.restfulcrud.dao.EstacionDAO;
import org.o7planning.restfulcrud.model.Deposit;
import org.o7planning.restfulcrud.model.Gas;
import org.o7planning.restfulcrud.model.Station;
import org.o7planning.restfulcrud.model.boolean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.sample.GasStation;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Contract;

/**
 *
 * @author willi
 */

@Path("estacion")
public class EstacionServicio {
    private EstacionDAO estacionDao;
    
    public EstacionServicio() {
      this.estacionDao= new EstacionDAO();
    }
    @GET
    @Path("/{idStation}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Station obtenerInformacionEstacion(@PathParam("idStation") int idStation) throws IOException, CipherException, Exception {
        Station estacion=this.estacionDao.getEstacion(idStation);
        return estacion;
    }
    @GET
    @Path("/deposito/{idStation}/{idCar}")
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean1 validarDeposito(@PathParam("idStation") int idStation,@PathParam("idCar") int idCar) throws IOException, CipherException, Exception {
        boolean1 res=new boolean1();
       boolean res1=this.estacionDao.ValidateDeposit(idStation, idCar);
        res.setValue(res1);
        return res;
    }
    
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Station RegistrarEstacion(Station estacion) throws IOException, CipherException, Exception {
        boolean res= this.estacionDao.RegistrarEstacion(estacion);
        return estacion;
    }
    
    @POST
    @Path("/terminarTransaccion")
    @Produces({ MediaType.APPLICATION_JSON })
    public Deposit RegistrarPago(Deposit deposito) throws IOException, CipherException, Exception {
        boolean res=this.estacionDao.RegistrarPago(deposito);
        return deposito;
    }
    
    
}
