package com.ramkumarbe.problem.rolehierarchy.dto;

public class Role {
	
	public Role(String roleName) {
		this.setRoleName(roleName);
	}
	
	public Role(String roleName, String reportingRole) {
		this.roleName = roleName;
		this.reportingRole = reportingRole;
	}

	public String getReportingRole() {
		return reportingRole;
	}
	public void setReportingRole(String reportingRole) {
		this.reportingRole = reportingRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private String reportingRole;
	private String roleName;
}
