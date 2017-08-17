package com.stargazerproject.net.impl;

import com.google.common.base.Optional;

public class KeystroreModel {

	private String filePath;

	private String password;

	public KeystroreModel(Optional<String> filePathArg, Optional<String> passwordArg) {
		filePath = filePathArg.get();
		password = passwordArg.get();
	}

	public Optional<String> getFilePath() {
		return Optional.of(filePath);
	}


	public Optional<String> getPassword() {
		return Optional.of(password);
	}
	

}
