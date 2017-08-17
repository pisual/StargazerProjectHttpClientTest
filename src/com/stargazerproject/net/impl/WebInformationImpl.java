package com.stargazerproject.net.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.staragzerproject.io.file.VirtualFile;
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
	public Optional<StringBuffer> httpSContent(Optional<String> url, VirtualFile<String, byte[]> keystrore) {
		// TODO Auto-generated method stub
		return null;
	}

}
