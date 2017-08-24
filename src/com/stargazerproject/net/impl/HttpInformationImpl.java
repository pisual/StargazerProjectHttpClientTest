package com.stargazerproject.net.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpInformation;
import com.stargazerproject.net.exception.HttpClientException;
import com.stargazerproject.net.exception.HttpEntityException;

public class HttpInformationImpl implements HttpInformation{

	@Override
	public Optional<StringBuffer> httpContent(Optional<String> url) throws HttpEntityException, ParseException, HttpClientException, IOException {
		CloseableHttpResponse closeableHttpResponse = closeableHttpResponseBuild(closeableHttpClientBuild(), new HttpGet(url.get()));
		HttpEntity entity = httpEntity(closeableHttpResponse);
		return httpPageContent(entity);
	}
	
	private CloseableHttpClient closeableHttpClientBuild() throws HttpClientException{
		CloseableHttpClient httpClient = HttpClients.custom().build();
		if(httpClient == null){
			throw new HttpClientException("HttpClient Can't Read");
		}
		return HttpClients.custom().build();
	}
	
	private CloseableHttpResponse closeableHttpResponseBuild(CloseableHttpClient closeableHttpClient, HttpGet httpGet) throws ClientProtocolException, IOException {
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
		finally{
			try {
				closeableHttpResponse.close();
			} 
			catch (IOException iOException) {
				throw iOException;
			}
		}
	}
	
	private HttpEntity httpEntity(CloseableHttpResponse closeableHttpResponse) throws HttpEntityException{
		HttpEntity entity = closeableHttpResponse.getEntity();
		if(entity == null){
			throw new HttpEntityException("HttpEntity Can't Read");
		}
		return entity;
	}
	
	private Optional<StringBuffer> httpPageContent(HttpEntity entity) throws ParseException, IOException{
		StringBuffer result = new StringBuffer();
		result.append(EntityUtils.toString(entity));
		EntityUtils.consume(entity);
		return Optional.of(result);
	}
	
}
