package com.stargazerproject.net.base;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.exception.HttpEntityException;

public class BaseNetConnect {
	
	public BaseNetConnect() {}
	
	public HttpGet httpGetBuild(Optional<String> url){
		return new HttpGet(url.get());
	}
	
	protected CloseableHttpResponse closeableHttpResponseBuild(CloseableHttpClient closeableHttpClient, HttpGet httpGet) throws ClientProtocolException, IOException {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			closeableHttpResponse = closeableHttpClient.execute(httpGet);
			return closeableHttpResponse;
			}
		
		catch (ClientProtocolException clientProtocolException) {
			throw clientProtocolException;
			} 
		
		catch (IOException iOException) {
			throw iOException;
		}
//		finally{
//			try {
//				closeableHttpResponse.close();
//			} 
//			catch (IOException iOException) {
//				throw iOException;
//			}
//		}
	}
	
	protected HttpEntity httpEntity(CloseableHttpResponse closeableHttpResponse) throws HttpEntityException{
		HttpEntity entity = closeableHttpResponse.getEntity();
		if(entity == null){
			throw new HttpEntityException("HttpEntity Can't Read");
		}
		return entity;
	}
	
	protected Optional<StringBuffer> httpPageContent(HttpEntity entity) throws ParseException, IOException{
		StringBuffer result = new StringBuffer();
		result.append(EntityUtils.toString(entity));
		EntityUtils.consume(entity);
		return Optional.of(result);
	}
	
}
