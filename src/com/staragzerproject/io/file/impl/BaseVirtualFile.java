package com.staragzerproject.io.file.impl;

import com.google.common.base.Optional;
import com.staragzerproject.io.file.VirtualFile;
import com.stargazerproject.cache.BigCache;

public class BaseVirtualFile implements VirtualFile<String, byte[]>{
	
	private BigCache<String, byte[]> bigCache;
	
	private FileModel fileModel;
	
	protected BaseVirtualFile(BigCache<String, byte[]> bigCacheArg, Optional<String> fileIDArg) {
		bigCache = bigCacheArg;
		fileModel = new FileModel(fileIDArg);
	}

	@Override
	public byte[] read() {
		return bigCache.get(fileModel.IDSequence());
	}
	
	@Override
	public void write(byte[] v) {
		bigCache.add(fileModel.IDSequence(), v);
	}
	
	@Override
	public void delete() {
		bigCache.remove(fileModel.IDSequence());
	}

}
