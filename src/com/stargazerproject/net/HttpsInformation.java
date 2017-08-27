package com.stargazerproject.net;

import com.google.common.base.Optional;

public interface HttpsInformation {
	
	public Optional<StringBuffer> httpSContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url, Optional<SSLConnectionSocket> sSLConnectionSocket) throws Exception;
}
