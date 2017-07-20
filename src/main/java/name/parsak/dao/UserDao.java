

// Data Access Object
package name.parsak.dao;

import java.util.List;

import name.parsak.dto.MenuDto;
import name.parsak.dto.UsrDptRole;
import name.parsak.model.Department;
import name.parsak.model.Menu;
import name.parsak.model.Role;
import name.parsak.model.User;
import name.parsak.model.UserRole;


public interface UserDao {
	
	public void addUser(User user);
	public void updateUser(User user);
	public void removeUser(User user);
	public User getUserById(int id);
	public User getUser(long id, String password);
	public List<User> listUsers();	
	
	public Department getDepartmentByName(String deptname);
	public void AddDept(String deptname);
	public Role getRoleByName(String rolename);
	public void AddRole(String rolename);

	public UserRole GetUserRole(User user, Department dept, Role role);
	public List<UserRole> getUserRoles(Department dept, Role role);
	public void AddUserRole(User user, Department dept, Role role);

	public List<UsrDptRole> getUsrDptRoles(User user);
	public void removeUserRoleById(long id);
	
	public List<UsrDptRole> listDepartments();
	public List<UsrDptRole> listRoles();
	
	public List<UsrDptRole> getDepartmentUsers(Department department);
	
	public void RemoveUserRoleByUser(User user);
	public List<Menu> getMenuByParent(long parent);
	public void AddMenu(Menu menu);
	public MenuDto getMenuDtoById(long id);
	
}