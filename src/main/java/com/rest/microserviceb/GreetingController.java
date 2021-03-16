package com.rest.microserviceb;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	//private static final String template = "Hello, %s!";
	//private final AtomicLong counter = new AtomicLong();

	@GetMapping("/callMicroserviceB")
	public String greeting(@RequestParam(value = "message", defaultValue = "Default Response") String message) {
		return message;
	}
	
	@GetMapping("/BcallMicroserviceA")
	public String greetingBA(@RequestParam(value = "message", defaultValue = "Default Response") String message) {
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			RequestBuilder reqbuilder = RequestBuilder.get();
			
			RequestBuilder reqbuilder1 = reqbuilder.setUri("http://microservice-a:8080/callMicroserviceA");
			RequestBuilder reqbuilder2 = reqbuilder1
		    		.addParameter("message",message);
			
			HttpUriRequest httpGet = reqbuilder2.build();
			HttpResponse httpresponse;
			httpresponse = httpclient.execute(httpGet);
			
			
			 String responseBody = EntityUtils.toString(httpresponse.getEntity());
			 
			 if(httpresponse.getStatusLine().getStatusCode() == 200) {
				 return responseBody;
			 }else {
				 return "Error Contacting Microservice A from Microservice B";
			 }
		 
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Contacting Microservice A from Microservice B";
		}
	
	}
}
