package com.btc123.app.validation;


import com.btc123.app.exception.ProParamException;

@FunctionalInterface
public interface Validator<T> {

	public abstract void notNull(T t) throws ProParamException;

}
