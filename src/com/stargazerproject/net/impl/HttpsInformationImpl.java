package com.stargazerproject.net.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpsInformation;
import com.stargazerproject.net.exception.HttpClientException;
import com.stargazerproject.net.exception.HttpEntityException;

public class HttpsInformationImpl implements HttpsInformation{
	
	private String keystrorefilePath;
	private String keystrorepassword;
	
	public HttpsInformationImpl() {}
	
	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url) {
		return null;
	}

	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url, KeystroreModel keystroreModel) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, HttpClientException, HttpEntityException, IOException {
		CloseableHttpResponse closeableHttpResponse = closeableHttpResponseBuild(closeableHttpClient(), new HttpGet(url.get()));
		HttpEntity entity = httpEntity(closeableHttpResponse);
		return httpPageContent(entity);
	}
	
	private FileInputStream keyStoreFileInputStream(String filePath) throws FileNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		return fileInputStream;
	}
	
	private KeyStore keyStoreLoad() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keyStoreFileInputStream(keystrorefilePath), keystrorepassword.toCharArray());
		return keyStore;
	}
	
	private SSLContext sSLContextBuild() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException{
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStoreLoad(), keystrorepassword.toCharArray()).build();
		return sslcontext;
	}
	
	private SSLConnectionSocketFactory sSLConnectionSocketFactory() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		SSLConnectionSocketFactory sSLConnectionSocketFactory = new SSLConnectionSocketFactory(sSLContextBuild());
		return sSLConnectionSocketFactory;
	}
	
	private CloseableHttpClient closeableHttpClient() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException, HttpClientException{
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sSLConnectionSocketFactory()).build();
		if(closeableHttpClient == null){
			throw new HttpClientException("HttpClient Can't Read");
		}
		return closeableHttpClient;
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
