package com.ramkumarbe.problem.rolehierarchy.ui.addrole;

import java.util.Scanner;

import com.ramkumarbe.problem.rolehierarchy.dto.Role;

public class AddRole {
	private AddRoleViewModel viewModel;
	
	public AddRole() {
		viewModel = new AddRoleViewModel(this);
	}
	
	public void addRole() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter root role name: ");
		String rootName = sc.next();
		Role role = new Role(rootName);
	}

	public void addSubRole() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter sub role name: ");
		String subRole = sc.nextLine();
		System.out.print("Enter reporting to role name: ");
		String reportingRole = sc.nextLine();
		Role role = new Role(subRole,reportingRole);
		System.out.println(role.getRoleName());
		viewModel.addRole(role);
	}
}
