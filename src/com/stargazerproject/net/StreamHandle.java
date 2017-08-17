package com.stargazerproject.net;

import java.util.Collection;
import java.util.stream.Stream;

import com.google.common.base.Optional;

public interface StreamHandle<E> {
	
	public Optional<Collection<E>> streamRegularHandle(Optional<E> src, Optional<E> regular);
	
	public Optional<Collection<E>> streamRegularHandle(Optional<Stream<E>> stream);
	
}
