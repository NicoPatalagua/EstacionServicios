package org.web3j.sample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class GasStation extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610a70806100206000396000f3fe6080604052600436106100865760003560e01c8063599a601511610059578063599a601514610171578063636fa6a7146101b3578063db865758146101dd578063de6b42c614610221578063e426c3681461028357610086565b80630e38fd7b1461008b57806311caec00146100bd5780631fec1e5c146100fc57806357e968991461012e575b600080fd5b34801561009757600080fd5b506100bb600480360360408110156100ae57600080fd5b50803590602001356102bf565b005b3480156100c957600080fd5b506100bb600480360360808110156100e057600080fd5b5080359060ff6020820135169060408101359060600135610307565b6100bb6004803603608081101561011257600080fd5b5080359060208101359060ff60408201351690606001356103cc565b34801561013a57600080fd5b506101586004803603602081101561015157600080fd5b50356104ea565b6040805192835260208301919091528051918290030190f35b34801561017d57600080fd5b506101a16004803603604081101561019457600080fd5b508035906020013561053a565b60408051918252519081900360200190f35b3480156101bf57600080fd5b506101a1600480360360208110156101d657600080fd5b503561055c565b3480156101e957600080fd5b5061020d6004803603604081101561020057600080fd5b508035906020013561056e565b604080519115158252519081900360200190f35b34801561022d57600080fd5b506102516004803603604081101561024457600080fd5b5080359060200135610593565b6040518084600181111561026157fe5b60ff168152602001838152602001828152602001935050505060405180910390f35b34801561028f57600080fd5b506100bb600480360360808110156102a657600080fd5b5080359060208101359060408101359060600135610639565b600082815260208190526040902080546001600160a01b03191633178155670de0b6b3a76400009091026002820155600180820192909255600501805460ff19169091179055565b61030f610a10565b604051806080016040528085600181111561032657fe5b8152670de0b6b3a7640000850260208083019190915260408083018690526001606090930183905260008981528083529081206003018054808501808355918352929091208451600490930201805494955090938593919291839160ff191690838181111561039157fe5b021790555060208201516001820155604082015160028201556060909101516003909101805460ff1916911515919091179055505050505050565b60008481526020819052604090206005015460ff1615806103fd575060008481526020819052604090206002015434105b1561040757600080fd5b600084815260208181526040808320868452600401909152902080546001600160a01b0319163317815560038101829055600201805483919060ff19166001838181111561045157fe5b021790555060008481526020818152604080832086845260049081019092529182902034918101829055600501805460ff1916600190811790915591517fd9c99beafe1e5cedc77d3dac6c4dcc2503d8640c1c24929489c60a06eff5348792859285929091819085908111156104c357fe5b60ff168152602001838152602001828152602001935050505060405180910390a150505050565b6000818152602081905260408120600501548190819060ff1615156001141561052157506000838152602081905260409020600301545b6000938452602084905260409093206002015493915050565b6000918252602082815260408084209284526004928301909152909120015490565b60009081526001602052604090205490565b6000918252602082815260408084209284526004909201905290206005015460ff1690565b6000828152602081905260408120600301805482918291859081106105b457fe5b60009182526020808320600490920290910154878352908290526040909120600301805460ff90921691869081106105e857fe5b906000526020600020906004020160010154600080888152602001908152602001600020600301868154811061061a57fe5b9060005260206000209060040201600201549250925092509250925092565b6040517f29f4acb0ed8a5b2201afd96e6e3bcbca8f3ea01c1a7ccdacf8c292a92d60067f90600090a161066c848461056e565b151560011415610086576040517f29f4acb0ed8a5b2201afd96e6e3bcbca8f3ea01c1a7ccdacf8c292a92d60067f90600090a17f62550916675f79fb24eb034c5525ea082c1ee5d55915b3599fa9760af26e4ae38160008087815260200190815260200160002060030184815481106106e157fe5b906000526020600020906004020160010154026040518082815260200191505060405180910390a160008481526020819052604090208054600390910180546001600160a01b03909216916108fc9184918690811061073c57fe5b906000526020600020906004020160010154029081150290604051600060405180830381858888f1935050505015801561077a573d6000803e3d6000fd5b507f62550916675f79fb24eb034c5525ea082c1ee5d55915b3599fa9760af26e4ae38160008087815260200190815260200160002060030184815481106107bd57fe5b600091825260208083206004928302016001015489845283825260408085208a865284018352938490209092015483519490920290910383529051918290030190a1600084815260208181526040808320868452600481018352908320548784529290915260030180546001600160a01b03909216916108fc9184918690811061084357fe5b90600052602060002090600402016001015402600080888152602001908152602001600020600401600087815260200190815260200160002060040154039081150290604051600060405180830381858888f193505050501580156108ac573d6000803e3d6000fd5b508060008086815260200190815260200160002060030183815481106108ce57fe5b90600052602060002090600402016002016000828254039250508190555080600080868152602001908152602001600020600301838154811061090d57fe5b600091825260208083206004928302016001015488845283825260408085208986528085018452908520909301805495909102909403909355868252915260030180548291908490811061095d57fe5b600091825260208083206004928302016001908101548985528183526040808620969091029095558382528484208885528301825284842080546001600160a01b031916815590810184905560028101805460ff199081169091556003820185905592810193909355600590920180549091169055815185815290810183905281517f64c275216a8c945a028374454f2413798aafdd97a4a48b13bae077045e166a4b929181900390910190a150505050565b604080516080810190915280600081526020016000815260200160008152602001600015158152509056fea265627a7a72315820952e7bfc7b179176517d7f4fc0ed268a4ac77c1ac20a5736facb8c8b1775a5c564736f6c634300050c0032";

    public static final String FUNC_SALDOESTACION = "SaldoEstacion";

    public static final String FUNC_CONSULTARSALDOESTACION = "consultarSaldoEstacion";

    public static final String FUNC_GETGASINFO = "getGasInfo";

    public static final String FUNC_GETSTATIONGASINFO = "getStationGasInfo";

    public static final String FUNC_SENDDEPOSIT = "sendDeposit";

    public static final String FUNC_SENDFUEL = "sendFuel";

    public static final String FUNC_SETSTATION = "setStation";

    public static final String FUNC_SETSTATIONFUEL = "setStationFuel";

    public static final String FUNC_VERIFICARDEPOSIT = "verificarDeposit";

    public static final Event CONSIGNACIONREALIZADA_EVENT = new Event("consignacionRealizada", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ECHO_EVENT = new Event("echo", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event ENTREGACOMBUSTIBLE_EVENT = new Event("entregaCombustible", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ERROR_EVENT = new Event("error", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event VALOR_EVENT = new Event("valor", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event VIEWGASINFO_EVENT = new Event("viewGasInfo", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected GasStation(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GasStation(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ConsignacionRealizadaEventResponse> getConsignacionRealizadaEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONSIGNACIONREALIZADA_EVENT, transactionReceipt);
        ArrayList<ConsignacionRealizadaEventResponse> responses = new ArrayList<ConsignacionRealizadaEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ConsignacionRealizadaEventResponse typedResponse = new ConsignacionRealizadaEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tipo = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cantidadSolicitada = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.monto = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ConsignacionRealizadaEventResponse> consignacionRealizadaEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ConsignacionRealizadaEventResponse>() {
            @Override
            public ConsignacionRealizadaEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONSIGNACIONREALIZADA_EVENT, log);
                ConsignacionRealizadaEventResponse typedResponse = new ConsignacionRealizadaEventResponse();
                typedResponse.log = log;
                typedResponse.tipo = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cantidadSolicitada = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.monto = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ConsignacionRealizadaEventResponse> consignacionRealizadaEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONSIGNACIONREALIZADA_EVENT));
        return consignacionRealizadaEventObservable(filter);
    }

    public List<EchoEventResponse> getEchoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ECHO_EVENT, transactionReceipt);
        ArrayList<EchoEventResponse> responses = new ArrayList<EchoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EchoEventResponse typedResponse = new EchoEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<EchoEventResponse> echoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, EchoEventResponse>() {
            @Override
            public EchoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ECHO_EVENT, log);
                EchoEventResponse typedResponse = new EchoEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<EchoEventResponse> echoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ECHO_EVENT));
        return echoEventObservable(filter);
    }

    public List<EntregaCombustibleEventResponse> getEntregaCombustibleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ENTREGACOMBUSTIBLE_EVENT, transactionReceipt);
        ArrayList<EntregaCombustibleEventResponse> responses = new ArrayList<EntregaCombustibleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EntregaCombustibleEventResponse typedResponse = new EntregaCombustibleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.Vehiculo = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cantidadSolicitada = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<EntregaCombustibleEventResponse> entregaCombustibleEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, EntregaCombustibleEventResponse>() {
            @Override
            public EntregaCombustibleEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ENTREGACOMBUSTIBLE_EVENT, log);
                EntregaCombustibleEventResponse typedResponse = new EntregaCombustibleEventResponse();
                typedResponse.log = log;
                typedResponse.Vehiculo = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cantidadSolicitada = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<EntregaCombustibleEventResponse> entregaCombustibleEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ENTREGACOMBUSTIBLE_EVENT));
        return entregaCombustibleEventObservable(filter);
    }

    public List<ErrorEventResponse> getErrorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERROR_EVENT, transactionReceipt);
        ArrayList<ErrorEventResponse> responses = new ArrayList<ErrorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorEventResponse typedResponse = new ErrorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.error = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorEventResponse> errorEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorEventResponse>() {
            @Override
            public ErrorEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERROR_EVENT, log);
                ErrorEventResponse typedResponse = new ErrorEventResponse();
                typedResponse.log = log;
                typedResponse.error = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ErrorEventResponse> errorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERROR_EVENT));
        return errorEventObservable(filter);
    }

    public List<ValorEventResponse> getValorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALOR_EVENT, transactionReceipt);
        ArrayList<ValorEventResponse> responses = new ArrayList<ValorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValorEventResponse typedResponse = new ValorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.val = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ValorEventResponse> valorEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ValorEventResponse>() {
            @Override
            public ValorEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALOR_EVENT, log);
                ValorEventResponse typedResponse = new ValorEventResponse();
                typedResponse.log = log;
                typedResponse.val = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ValorEventResponse> valorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALOR_EVENT));
        return valorEventObservable(filter);
    }

    public List<ViewGasInfoEventResponse> getViewGasInfoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VIEWGASINFO_EVENT, transactionReceipt);
        ArrayList<ViewGasInfoEventResponse> responses = new ArrayList<ViewGasInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ViewGasInfoEventResponse typedResponse = new ViewGasInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.typeGas = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ViewGasInfoEventResponse> viewGasInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ViewGasInfoEventResponse>() {
            @Override
            public ViewGasInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VIEWGASINFO_EVENT, log);
                ViewGasInfoEventResponse typedResponse = new ViewGasInfoEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.typeGas = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ViewGasInfoEventResponse> viewGasInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VIEWGASINFO_EVENT));
        return viewGasInfoEventObservable(filter);
    }

    public RemoteCall<BigInteger> SaldoEstacion(BigInteger _station) {
        final Function function = new Function(FUNC_SALDOESTACION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_station)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> consultarSaldoEstacion(BigInteger _station, BigInteger Vehiculo) {
        final Function function = new Function(FUNC_CONSULTARSALDOESTACION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_station), 
                new org.web3j.abi.datatypes.generated.Uint256(Vehiculo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>> getGasInfo(BigInteger idStation, BigInteger idfuel) {
        final Function function = new Function(FUNC_GETGASINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(idStation), 
                new org.web3j.abi.datatypes.generated.Uint256(idfuel)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> getStationGasInfo(BigInteger idStation) {
        final Function function = new Function(FUNC_GETSTATIONGASINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(idStation)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> sendDeposit(BigInteger idStation, BigInteger idVehiculo, BigInteger tipo, BigInteger cantidadSolicitada, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDDEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(idStation), 
                new org.web3j.abi.datatypes.generated.Uint256(idVehiculo), 
                new org.web3j.abi.datatypes.generated.Uint8(tipo), 
                new org.web3j.abi.datatypes.generated.Uint256(cantidadSolicitada)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> sendFuel(BigInteger _station, BigInteger Vehiculo, BigInteger fuel, BigInteger cantidadSolicitada) {
        final Function function = new Function(
                FUNC_SENDFUEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_station), 
                new org.web3j.abi.datatypes.generated.Uint256(Vehiculo), 
                new org.web3j.abi.datatypes.generated.Uint256(fuel), 
                new org.web3j.abi.datatypes.generated.Uint256(cantidadSolicitada)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setStation(BigInteger id, BigInteger deposit) {
        final Function function = new Function(
                FUNC_SETSTATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint256(deposit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setStationFuel(BigInteger id, BigInteger tipo, BigInteger price, BigInteger cantidadDisp) {
        final Function function = new Function(
                FUNC_SETSTATIONFUEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint8(tipo), 
                new org.web3j.abi.datatypes.generated.Uint256(price), 
                new org.web3j.abi.datatypes.generated.Uint256(cantidadDisp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> verificarDeposit(BigInteger idStation, BigInteger idVehiculo) {
        final Function function = new Function(FUNC_VERIFICARDEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(idStation), 
                new org.web3j.abi.datatypes.generated.Uint256(idVehiculo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<GasStation> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GasStation.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<GasStation> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GasStation.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static GasStation load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GasStation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static GasStation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GasStation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ConsignacionRealizadaEventResponse {
        public Log log;

        public BigInteger tipo;

        public BigInteger cantidadSolicitada;

        public BigInteger monto;
    }

    public static class EchoEventResponse {
        public Log log;
    }

    public static class EntregaCombustibleEventResponse {
        public Log log;

        public BigInteger Vehiculo;

        public BigInteger cantidadSolicitada;
    }

    public static class ErrorEventResponse {
        public Log log;

        public String error;
    }

    public static class ValorEventResponse {
        public Log log;

        public BigInteger val;
    }

    public static class ViewGasInfoEventResponse {
        public Log log;

        public BigInteger id;

        public String typeGas;

        public BigInteger price;
    }
}
