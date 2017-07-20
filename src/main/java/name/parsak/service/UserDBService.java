
// Service Layer Object
package name.parsak.service;

import java.util.List;

import name.parsak.dto.MenuDto;
import name.parsak.dto.UsrDptRole;
import name.parsak.model.Department;
import name.parsak.model.Menu;
import name.parsak.model.Role;
import name.parsak.model.User;

public interface UserDBService {
  
	public void setup();
	
	public void addUser(User user);
	public void updateUser(User user);
	public void removeUser(User user);
	
	public User getUserById(int id);
	public User getUser(long id, String password);
	public boolean isUserAdmin(User user);
	public List<User> listUsers();	

	public void AddDepartment(String deptname);
	public void AddRole(String rolename);
	
	public Department getDepartmentByName(String deptname);
	public Role getRoleByName(String rolename);
	public void AddUserRole(User user, Department department, Role role);

	public List<UsrDptRole> getUsrDptRoles(User user);
	public void removeUserRoleById(long id);
	
	public List<UsrDptRole> listDepartments();
	public List<UsrDptRole> listRoles();
	
	public List<UsrDptRole> getDepartmentUsers(Department department);
	public void RemoveUserRoleByUser(User user);
	
	public List<Menu> getMenuByParent(long parent);
	public List<Menu> getMenuByParent();
	
	public void AddMenu(Menu menu);
	public MenuDto getMenuDtoById(long id);
	
  }