/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.web3j.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigDecimal;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/*

https://medium.com/coinmonks/ethereum-blockchain-hello-world-smart-contract-with-java-9b6ae2961ad1

*/


public class ApplicationLoad {
    private static final Logger log = LoggerFactory.getLogger(ApplicationLoad.class);

    public static void main(String[] args) throws Exception {
        new ApplicationLoad().run();
    }

    private void run() throws Exception {
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
        /*
        log.info("Sending Ether ..");
        //Se debe especificar la direccion de la billetera donde se va a realizar la transaccion
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x1d0D1Fb2F3B5dD979B15A0CcE3426c86E195770e",  // you can put any address here
                BigDecimal.valueOf(100), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                .sendAsync().get();
        log.info("Transaction complete : "
                + transferReceipt.getTransactionHash());
        
        */
         // Now lets deploy a smart contract
        log.info("Deploying smart contract");
        Helloworld contract = Helloworld.load("0x38c3af271dee2cf5fe74975d199101714883b776",web3j, credentials,ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);

        String contractAddress = contract.getContractAddress();
        log.info("Smart contract deployed to address " + contractAddress + " " + contract.getTransactionReceipt());

        log.info("Initial value of counter in Smart contract: " + contract.getCounter().send());
        log.info("Incrementing counter in Smart contract");
        contract.add().send();
        log.info("Value of counter in Smart contract after increment : " + contract.getCounter().send());
        /*
        log.info("Decrementing counter in Smart contract");
        contract.subtract().send();
        log.info("Final value of counter in Smart contract : " + contract.getCounter().send());
*/
        
    }
}