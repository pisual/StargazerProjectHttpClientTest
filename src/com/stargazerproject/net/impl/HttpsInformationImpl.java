package com.stargazerproject.net.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpsInformation;
import com.stargazerproject.net.SSLConnectionSocket;
import com.stargazerproject.net.base.BaseNetConnect;
import com.stargazerproject.net.exception.HttpClientException;
import com.stargazerproject.net.exception.HttpEntityException;

public class HttpsInformationImpl extends BaseNetConnect implements HttpsInformation{
	
	public HttpsInformationImpl() {}
	
	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url) {
		return null;
	}

	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url, Optional<SSLConnectionSocket> sSLConnectionSocket) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, HttpClientException, HttpEntityException, IOException {
		CloseableHttpClient closeableHttpClient = closeableHttpClientBuild(sSLConnectionSocket.get().sSLConnectionSocketFactoryInitialize());
		CloseableHttpResponse closeableHttpResponse = closeableHttpResponseBuild(closeableHttpClient, httpGetBuild(url));
		HttpEntity entity = httpEntity(closeableHttpResponse);
		return httpPageContent(entity);
	}
	
	
	private CloseableHttpClient closeableHttpClientBuild(SSLConnectionSocketFactory sSLConnectionSocketFactory) throws HttpClientException {
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sSLConnectionSocketFactory).build();
		if(closeableHttpClient == null){
			throw new HttpClientException("HttpClient Can't Read");
		}
		return closeableHttpClient;

	}
	
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore","/Users/Felixerio/SSL/hmog.me.keystrore");
		HttpsInformation httpsInformation = new HttpsInformationImpl();
		SSLConnectionSocket sSLConnectionSocket = new SSLBuildImpl(Optional.of("/Users/Felixerio/SSL/hmog.me.keystrore"), Optional.of("1074adce"), Optional.of("1074adce"));
		try {
			System.out.println(httpsInformation.httpSContent(Optional.of("http://www.llss.me/wp/52475.html"), Optional.of(sSLConnectionSocket)).get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
