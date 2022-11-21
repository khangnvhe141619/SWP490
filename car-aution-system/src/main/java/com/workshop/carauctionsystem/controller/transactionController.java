package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.lang.String;
import java.lang.*;
import java.util.concurrent.ExecutionException;

public class transactionController extends Contract {
    protected transactionController(String contractBinary, String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
        super(contractBinary, contractAddress, web3j, transactionManager, gasProvider);
    }

    protected transactionController(EnsResolver ensResolver, String contractBinary, String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
        super(ensResolver, contractBinary, contractAddress, web3j, transactionManager, gasProvider);
    }

    protected transactionController(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, gasProvider);
    }

    //    @PostMapping("/transfer")
    public void tranferCAB(){
        User user = new User();
        String address = user.getAddressWallet();
        //0x529e20d8Bb21ece25de55d73a19e88Be875AeAf3 - CC Coin Address
        //YourSmartContract contract = YourSmartContract.deploy.send();
        //Web3j web3 = new Web3j()

    }

    public static void main(String[] args) throws ExecutionException,InterruptedException{
        Web3j web3 = Web3j.build(new HttpService("https://testnet.bscscan.com/address/0xb025a25c903e423080e2422e4855af904590cbfa"));
        EthBlockNumber result = web3.ethBlockNumber().sendAsync().get();
        System.out.println(" The Block Number is: " + result.getBlockNumber().toString());
    }
}
