package com.stargazerproject.net.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.stargazerproject.net.HttpInformation;

public class HttpInformationImpl implements HttpInformation{

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
			}
			catch (ClientProtocolException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Optional.of(result);
	}

}
