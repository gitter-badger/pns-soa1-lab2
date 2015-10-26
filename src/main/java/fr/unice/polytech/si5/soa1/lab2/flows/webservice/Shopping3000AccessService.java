package fr.unice.polytech.si5.soa1.lab2.flows.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * Created by Tianhao on 10/25/2015.
 */
public interface Shopping3000AccessService {

    @WebMethod(operationName = "CatalogListAllItem")
    @WebResult(name="listItems")
    double listAllItems(@WebParam(name="request") String request);
}