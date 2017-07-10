package com.stargazerproject.net.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetTest {
public static void main(String[] args) throws IOException {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpGet httpGet = new HttpGet("http://hmog.me/s/59949");
	CloseableHttpResponse response1 = httpclient.execute(httpGet);
	// The underlying HTTP connection is still held by the response object
	// to allow the response content to be streamed directly from the network socket.
	// In order to ensure correct deallocation of system resources
	// the user MUST call CloseableHttpResponse#close() from a finally clause.
	// Please note that if response content is not fully consumed the underlying
	// connection cannot be safely re-used and will be shut down and discarded
	// by the connection manager. 
	try {
	    System.out.println(response1.getStatusLine());
	    HttpEntity entity1 = response1.getEntity();
	    // do something useful with the response body
	    // and ensure it is fully consumed
	    EntityUtils.consume(entity1);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	    response1.close();
	}
}
}
