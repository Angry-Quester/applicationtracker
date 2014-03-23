package edu.khai.applicationtracker.dao;

public interface SecuredDAO  {
	public boolean securityCheck(Long principalId, Long entityId);
}