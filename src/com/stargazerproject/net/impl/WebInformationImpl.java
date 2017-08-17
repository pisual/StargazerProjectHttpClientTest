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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.WebInformation;

public class WebInformationImpl implements WebInformation {

	@Override
	public Optional<StringBuffer> httpContent(Optional<String> url) {
		CloseableHttpClient httpClient = HttpClients.custom().build();
		StringBuffer result = new StringBuffer();
		if (httpClient != null) {
			HttpGet httpGet = new HttpGet(url.get());
			CloseableHttpResponse response;
			try {
				response = httpClient.execute(httpGet);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result.append(EntityUtils.toString(entity));
						EntityUtils.consume(entity);
					}
				} finally {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Optional.of(result);
	}
	
	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StringBuffer> httpSContent(Optional<String> url, KeystroreModel keystroreModel) {
		
		try {
			KeyStore keyStore = keyStoreLoad(keystroreModel);
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1074adce".toCharArray()).build();
		} catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | CertificateException | IOException e) {
			/**KeyStore Exception**/
			e.printStackTrace();
		}
		

		return null;
	}
	
	public KeyStore keyStoreLoad(KeystroreModel keystroreModel) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try {
			FileInputStream instream = keyStoreFileInputStream(keystroreModel.getFilePath().get());
			keyStore.load(instream, keystroreModel.getPassword().get().toCharArray());
			return keyStore;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalAccessError(e.getMessage());
		}
	}
	
	public FileInputStream keyStoreFileInputStream(String filePath){
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			return fileInputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalAccessError(e.getMessage());
		}
	}
	

}
