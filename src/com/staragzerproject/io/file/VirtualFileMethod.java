package com.staragzerproject.io.file;


public interface VirtualFileMethod<K, V> {
	
	public V read();
	
	public void write(V v);
	
	public void delete();
	
}
