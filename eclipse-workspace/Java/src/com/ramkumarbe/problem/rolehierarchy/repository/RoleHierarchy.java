package com.ramkumarbe.problem.rolehierarchy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String query = "insert into table_role (role_name,reporting_role) values('"+role.getRoleName()+"','"+role.getReportingRole()+"')";
		PreparedStatement preparedStatement 
		     = connection.prepareStatement(query);
		preparedStatement.execute();
	}
	public List<Role> getRolesList() {
		List<Role> rolesList = new ArrayList<>();
		try {
			rolesList = getRolesList(getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rolesList;
	}

	private List<Role> getRolesList(Connection connection) throws SQLException {
		String query ="SELECT * FROM table_role;";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
		List<Role> rolesList = new ArrayList<>();
		
        while(resultSet.next()) {
    		String roleName = resultSet.getString("role_name");
    		String reportingRoleName = resultSet.getString("reporting_role");
    	    Role role = new Role(roleName,reportingRoleName);
    	    rolesList.add(role);
        }
	    return rolesList;
	}
}
