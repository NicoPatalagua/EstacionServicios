/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.sample.ApplicationLoad;
import org.web3j.sample.Helloworld;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

@Path("/hello")
public class HelloWorldService {
 private static final Logger log = LoggerFactory.getLogger(HelloWorldService.class);
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();

	}
        
        
         @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String sayPlainTextHello() throws Exception {
            
            
             // We start by creating a new web3j instance to connect to remote nodes on the network.
        Web3j web3j = Web3j.build(new HttpService());
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        //Ubicacion del archivo de claves. Se debe poner la superclave del archivo
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "123456",
                        "C:\\CURSOS\\RESTBlockChain\\chaindata\\keystore\\UTC--2019-04-26T16-36-52.532795800Z--e73c30f5f9ff9b2bd66235a46b31aaad85a640d4");
        log.info("Credentials loaded");
         // Now lets load a smart contract
        log.info("Deploying smart contract");
        Helloworld contract = Helloworld.load("0xa807b8f7ea9a64c5d308a60361d7b5a0ac081466",web3j, credentials,ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);

        String contractAddress = contract.getContractAddress();
        log.info("Smart contract deployed to address " + contractAddress);

        log.info("Initial value of counter in Smart contract: " + contract.getCounter().send());
        log.info("Incrementing counter in Smart contract");
        contract.add().send();
        log.info("Value of counter in Smart contract after increment : " + contract.getCounter().send());
        
            return "Value of counter in Smart contract after increment : " + contract.getCounter().send();
        }
        
        

}