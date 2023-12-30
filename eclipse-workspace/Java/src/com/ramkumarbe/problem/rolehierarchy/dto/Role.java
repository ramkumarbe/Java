package com.ramkumarbe.problem.rolehierarchy.dto;

public class Role {
	
	public Role(String roleName) {
		this.setRoleName(roleName);
	}
	
	public Role getReportingRole() {
		return reportingRole;
	}
	public void setReportingRole(Role reportingRole) {
		this.reportingRole = reportingRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private Role reportingRole;
	private String roleName;
}
