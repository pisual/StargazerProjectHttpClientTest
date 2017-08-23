package com.stargazerproject.net.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 通过证书访问Https
 * **/
public class HttpClientHttpsTest {

	public static void httpsTest() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {

		System.setProperty("javax.net.ssl.trustStore","/Users/Felixerio/SSL/hmog.me.keystrore");

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());		
		FileInputStream instream = new FileInputStream(new File( "/Users/Felixerio/SSL/hmog.me.keystrore"));
		trustStore.load(instream, "1074adce".toCharArray());

		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(trustStore, "1074adce".toCharArray()).build();

		/*
		 * 通过忽略证书来进行认证
		 * 		 try {
			 SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				 public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					 return true;
					 }
				 }
			 }).build(); 
		 */
	
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		if (httpClient != null) {
			HttpGet httpGet = new HttpGet("https://hmog.me/");
			CloseableHttpResponse response = httpClient.execute(httpGet);

			try {
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					System.out.println(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		}

	}

	public static void main(String[] args) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		HttpClientHttpsTest.httpsTest();
		
		List test = new ArrayList();
		Map test2 = new HashMap();
	}
}