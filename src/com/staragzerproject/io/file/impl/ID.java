package com.staragzerproject.io.file.impl;

import com.google.common.base.Optional;

/** 
 *  @name 实体ID
 *  @illustrate ID序列
 *  @author Felixerio
 *  **/

public abstract class ID{

	/** @illustrate ID**/
	protected String id;

	public ID(Optional<String> idArg) {
		id = idArg.get();
	}
	
	public Optional<String> IDSequence(){
		return Optional.of(id);
	}
}
