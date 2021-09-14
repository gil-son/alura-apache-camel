package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class OrderRouter {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:orders?delay=5s&noop=true")
                	.split()
                		.xpath("/order/items/item")//.log("${body}") 
                	.filter()
                		.xpath("/item/format[text()='EBOOK']")
				.log("Message ${id}")  // .log("${body}")
				
				
				.marshal().xmljson()
				.setHeader("CamelFileName", simple("${file:name.noext}.json"))
				.log("${body}")
				.to("file:exit");

            }
        });
        context.start();
        Thread.sleep(20000);
        context.stop();
    }

}