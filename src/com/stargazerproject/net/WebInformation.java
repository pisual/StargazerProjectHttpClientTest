package com.stargazerproject.net;

import com.google.common.base.Optional;
import com.staragzerproject.io.file.VirtualFile;

public interface WebInformation {
	
	public Optional<StringBuffer> httpContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url);
	
	public Optional<StringBuffer> httpSContent(Optional<String> url, VirtualFile<String, byte[]> keystrore);
	
}
