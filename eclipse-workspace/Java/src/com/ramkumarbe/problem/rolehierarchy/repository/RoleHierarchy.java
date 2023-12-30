package com.ramkumarbe.problem.rolehierarchy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ramkumarbe.problem.rolehierarchy.dto.Role;

public class RoleHierarchy {

	private static RoleHierarchy obj;
	private RoleHierarchy() { }
	
	public static RoleHierarchy getInstance() {
		if(obj == null) {
			obj = new RoleHierarchy();
		}
		return obj;
	}
	
	private Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/role_hierarchy";
		String username = "root";
		String password = "1234";

		return DriverManager.getConnection(url, username, password);
	}
    
	public void addRole(Role role) {
		try {
			addRole(getConnection(), role);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void addRole(Connection connection, Role role) throws SQLException {
		PreparedStatement preparedStatement 
		     = connection.prepareStatement("insert into table_role (role_name,reporting_role) values('"+role.getRoleName()+"','"+role.getReportingRole()+"')");
		preparedStatement.execute();
	}
//	public Role getRole() {
//		Role role = null;
//		try {
//			role = getRole(getConnection());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return role;
//	}

//	private Role getRole(Connection connection) {
//		PreparedStatement prepareStatement 
//	     = connection.prepareStatement("insert into table_role (role_name)values('"+",')");
//	
//	}
}
