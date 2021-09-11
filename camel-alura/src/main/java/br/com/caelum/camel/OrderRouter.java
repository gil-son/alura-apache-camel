package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class OrderRouter {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes( new RouteBuilder() {  						// Anonymous Classes
			
			@Override
			public void configure() throws Exception{
				from("file:orders?delay=5s")
				.log("Message ${id}")
				.to("file:exit");
				
				
			}
		});
		
		context.start();
		Thread.sleep(0);
		context.stop();
	}
}
