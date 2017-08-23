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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpsInformation;

public class HttpsInformationImpl implements HttpsInformation{
	
	private String keystrorefilePath;
	private String keystrorepassword;
	
	public HttpsInformationImpl() {
	}
	
	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url, KeystroreModel keystroreModel) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, IOException {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient httpClient = httpClientBuild();
		if (httpClient != null) {
			HttpGet httpGet = new HttpGet(url.get());
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result.append(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		}
		
		return Optional.of(result);
	}
	
	public FileInputStream keyStoreFileInputStream(String filePath) throws FileNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		return fileInputStream;
	}
	
	public KeyStore keyStoreLoad() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
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
	
	private CloseableHttpClient httpClientBuild() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException{
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sSLConnectionSocketFactory()).build();
		return closeableHttpClient;
	}
	
}
