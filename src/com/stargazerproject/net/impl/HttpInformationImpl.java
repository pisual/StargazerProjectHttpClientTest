package com.stargazerproject.net.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpInformation;
import com.stargazerproject.net.base.BaseNetConnect;
import com.stargazerproject.net.exception.HttpClientException;
import com.stargazerproject.net.exception.HttpEntityException;

public class HttpInformationImpl extends BaseNetConnect implements HttpInformation{

	@Override
	public Optional<StringBuffer> httpContent(Optional<String> url) throws HttpEntityException, ParseException, HttpClientException, IOException {
		CloseableHttpResponse closeableHttpResponse = closeableHttpResponseBuild(closeableHttpClientBuild(), httpGetBuild(url));
		HttpEntity entity = httpEntity(closeableHttpResponse);
		return httpPageContent(entity);
	}
	
	protected CloseableHttpClient closeableHttpClientBuild() throws HttpClientException{
		CloseableHttpClient httpClient = HttpClients.custom().build();
		if(httpClient == null){
			throw new HttpClientException("HttpClient Can't Read");
		}
		return HttpClients.custom().build();
	}
	
}
