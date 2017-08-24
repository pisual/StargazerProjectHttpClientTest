package com.stargazerproject.net;

import com.google.common.base.Optional;

public interface HttpInformation {
	public Optional<StringBuffer> httpContent(Optional<String> url) throws Exception;
}
