package com.stargazerproject.net;

import com.google.common.base.Optional;
import com.stargazerproject.net.impl.KeystroreModel;

public interface WebInformation {
	
	public Optional<StringBuffer> httpContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url, KeystroreModel keystroreModel);
	
}
