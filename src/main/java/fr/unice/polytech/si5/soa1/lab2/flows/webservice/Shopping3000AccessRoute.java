package fr.unice.polytech.si5.soa1.lab2.flows.webservice;

import fr.unice.polytech.si5.soa1.lab2.flows.processors.Translater;
import org.apache.camel.builder.RouteBuilder;

import static fr.unice.polytech.si5.soa1.lab2.flows.utils.Endpoints.*;

/**
 * Created by Tianhao on 10/25/2015.
 */
public class Shopping3000AccessRoute  extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // Retrieve a list
        from("direct:listItems")
                .log("listItem with ${body}")
                .to(HANDLE_FULL_CATALOG_LIST)
                .log("all list done ${body}")
                .process(Translater.tranlaterTable2List)
//                .log("after translate ${body}")
//                .setBody(simple("${body}"))
        ;

        from("direct:getItem")
                .setBody(simple("${body[0]}")) // get first parameter
                .log("getItem with ${body.left} ${body.right}")
                .to(HANDLE_FULL_CATALOG_GET_ITEM)
                .setBody(simple("${body}"))
        ;

        from("direct:badRequest")
                .log("This is a bad request!")
        ;


        from("cxf:/Shopping3000AccessService?serviceClass=fr.unice.polytech.si5.soa1.lab2.flows.webservice.Shopping3000AccessService")
                .choice()
                .when(simple("${in.headers.operationName} == 'CatalogListAllItems'"))
                .log("CatalogListAllItems")
                .to("direct:listItems")
                .process(Translater.tranlaterTable2List)
                .when(simple("${in.headers.operationName} == 'CatalogGetItem'"))
                .log("CatalogGetItem")
                .to("direct:getItem")
                .otherwise()
                .to("direct:badRequest").stop()
                .endChoice()
        ;
    }
}
