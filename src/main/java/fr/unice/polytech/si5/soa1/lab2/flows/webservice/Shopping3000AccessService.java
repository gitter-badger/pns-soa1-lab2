package fr.unice.polytech.si5.soa1.lab2.flows.webservice;

import fr.unice.polytech.si5.soa1.lab2.flows.business.minibo.Item;
import fr.unice.polytech.si5.soa1.lab2.flows.business.shopping3000.Shopping3000ID;
import fr.unice.polytech.si5.soa1.lab2.flows.business.shopping3000.Shopping3000Item;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Tianhao on 10/25/2015.
 */
@WebService(serviceName = "Shopping3000AccessService")
public interface Shopping3000AccessService {

    @WebMethod(operationName = "CatalogListAllItems")
    @WebResult(name="listItems")
    List<Object> listAllItems();

    @WebMethod(operationName = "CatalogGetItem")
    @WebResult(name="item")
    Item getItem(@WebParam(name = "Shopping3000ID") Shopping3000ID id);
}
