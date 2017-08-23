package com.stargazerproject.net;

import com.google.common.base.Optional;
import com.stargazerproject.net.impl.KeystroreModel;

public interface HttpsInformation {
	
	public Optional<StringBuffer> httpSContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url, KeystroreModel keystroreModel) throws Exception;
}
