package com.documentpro.service;

public interface JwtService {

	public void authenticate(String userName, String password) throws Exception;
}
