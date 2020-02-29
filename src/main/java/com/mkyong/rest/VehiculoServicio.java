    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.o7planning.restfulcrud.dao.EstacionDAO;
import org.o7planning.restfulcrud.dao.VehiculoDAO;
import org.o7planning.restfulcrud.model.Deposit;
import org.o7planning.restfulcrud.model.Station;
import org.web3j.crypto.CipherException;

/**
 *
 * @author willi
 */

@Path("vehiculo")
public class VehiculoServicio {
    private VehiculoDAO vehiculoDao;
    
    public VehiculoServicio() {
      this.vehiculoDao= new VehiculoDAO();
    }
    
    @GET
    @Path("/estacion/{idStation}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Station obtenerInformacionEstacion(@PathParam("idStation") int idStation) throws IOException, CipherException, Exception {
        Station estacion=this.vehiculoDao.getEstacion(idStation);
        return estacion;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/enviarDeposito")
    public Deposit SendDeposit(Deposit deposito) throws IOException, CipherException, Exception {
            boolean res=this.vehiculoDao.sendDeposit(deposito);
        return deposito;
    }
}
