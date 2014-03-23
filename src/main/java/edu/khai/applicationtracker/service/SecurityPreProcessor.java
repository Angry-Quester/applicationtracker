package edu.khai.applicationtracker.service;


public interface SecurityPreProcessor {
	public boolean preProcess(SecuredService securedService, Long principalId, Long entityId);
}