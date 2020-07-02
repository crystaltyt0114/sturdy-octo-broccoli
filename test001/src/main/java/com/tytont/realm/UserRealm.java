package com.tytont.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tytont.model.Permission;
import com.tytont.model.Role;
import com.tytont.model.User;
import com.tytont.service.PermissionService;
import com.tytont.service.RoleService;
import com.tytont.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = (String) pc.getPrimaryPrincipal();
		User user = userService.selectUserByUsername(username, "123");
		// 角色
		Role role = roleService.selectById(user.getRoleid());
		info.addRole(role.getRolename());
		// 权限
		List<Permission> selectUserByRoleId = permissionService.selectUserByRoleId(role.getId());
		List<String> sList = new ArrayList<String>();
		selectUserByRoleId.forEach(per -> {
			sList.add(per.getName());
		});
		info.addStringPermissions(sList);
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
		AuthenticationInfo info;
		String username = (String) at.getPrincipal();
		String password = new String((char[]) at.getCredentials());
		User user = userService.selectUserByUsername(username, password);
		if (user != null) {
			info = new SimpleAuthenticationInfo(username, at.getCredentials(), this.getName());
			return info;
		}
		return null;
	}

}
