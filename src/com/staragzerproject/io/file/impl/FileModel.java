package com.staragzerproject.io.file.impl;

import com.google.common.base.Optional;

public final class FileModel extends ID{
	/**文件长度**/
	private Integer fileLenght = 0;
	

	public FileModel(Optional<String> idArg) {
		super(idArg);
	}
	
	public void fileLenghtIncrease(Optional<Integer> fileLenghtSlice){
		fileLenght = fileLenght + fileLenghtSlice.get();
	}
}
