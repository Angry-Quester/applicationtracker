package edu.khai.applicationtracker.service;


public interface SecuredService {
	public boolean securityCheck(Long principalId, Long entityId);
}