package com.ramkumarbe.problem.rolehierarchy.ui.addrole;

import com.ramkumarbe.problem.rolehierarchy.dto.Role;
import com.ramkumarbe.problem.rolehierarchy.repository.RoleHierarchy;

public class AddRoleViewModel {
	private AddRole view;
	public AddRoleViewModel(AddRole view) {
		this.view = view;
	}
	public void addRoleLister(Role role) {
		RoleHierarchy.getInstance().addRole(role);
	}
	public void displayRolesListener() {
		view.printRoles(RoleHierarchy.getInstance().getRolesList());
	}
}
