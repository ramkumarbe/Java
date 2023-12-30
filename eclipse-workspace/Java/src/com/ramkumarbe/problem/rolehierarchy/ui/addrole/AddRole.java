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
		viewModel.addRole(role);
		System.out.println(role.getRoleName());
	}
}
