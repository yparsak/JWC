package name.parsak.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import name.parsak.dao.UserDao;
import name.parsak.dto.MenuDto;
import name.parsak.dto.UsrDptRole;
import name.parsak.model.Department;
import name.parsak.model.Menu;
import name.parsak.model.Role;
import name.parsak.model.User;
import name.parsak.model.UserRole;


public class UserDBServiceImpl implements UserDBService {
	
	private final String ADMIN = "ADMIN";
	private final String blank = "";
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public void setup() {
		
		this.userDao.AddDept(ADMIN);
		this.userDao.AddRole(ADMIN);
		
		Department admin_dept = this.userDao.getDepartmentByName(ADMIN);
		Role admin_role       = this.userDao.getRoleByName(ADMIN);
		if (admin_dept != null && admin_role != null) {
			List<UserRole> userroles = this.userDao.getUserRoles(admin_dept, admin_role);
			if (userroles.isEmpty() || userroles == null) {
				User user = new User();
				user.setName(ADMIN);
				user.setLastname(ADMIN);
				user.setEmail(blank);
				user.setPassword(ADMIN);
				this.userDao.addUser(user);
				this.userDao.AddUserRole(user, admin_dept, admin_role);
			}
		}
	}

	@Transactional
	public void addUser(User user) {
		this.userDao.addUser(user);
	}
	@Transactional
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	@Transactional
	public void removeUser(User user) {
		this.userDao.RemoveUserRoleByUser(user);
		this.userDao.removeUser(user);
	}

	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}
	@Transactional
	public User getUser(long id, String password) {
		return this.userDao.getUser(id, password);
	}
	@Transactional
	public boolean isUserAdmin(User user) {
		Department admin_dept = this.userDao.getDepartmentByName(ADMIN);
		Role admin_role       = this.userDao.getRoleByName(ADMIN);	
		if (admin_dept != null && admin_role != null) {
			UserRole userrole = this.userDao.GetUserRole(user, admin_dept, admin_role);
			if (userrole != null) return true;
		}
		return false;
	}

	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}
	
	
	@Transactional
	public void AddDepartment(String deptname) {
		this.userDao.AddDept(deptname);
	}

	@Transactional
	public void AddRole(String rolename) {
		this.userDao.AddRole(rolename);
	}

	@Transactional
	public Department getDepartmentByName(String deptname) {
		return this.userDao.getDepartmentByName(deptname);
	}

	@Transactional
	public Role getRoleByName(String rolename) {
		return this.userDao.getRoleByName(rolename);
	}
	
	@Transactional
	public void AddUserRole(User user, Department department, Role role) {
		this.userDao.AddUserRole(user, department, role);	
	}

	@Transactional
	public List<UsrDptRole> getUsrDptRoles(User user) {
		return this.userDao.getUsrDptRoles(user);
	}

	@Transactional
	public void removeUserRoleById(long id) {
		this.userDao.removeUserRoleById(id);
	}

	@Transactional
	public List<UsrDptRole> listDepartments() {
		return this.userDao.listDepartments();
	}

	@Transactional
	public List<UsrDptRole> listRoles() {
		return this.userDao.listRoles();
	}

	@Transactional
	public List<UsrDptRole> getDepartmentUsers(Department department) {
		return this.userDao.getDepartmentUsers(department);
	}

	@Transactional
	public void RemoveUserRoleByUser(User user) {
		this.userDao.RemoveUserRoleByUser(user);
	}

	@Transactional
	public List<Menu> getMenuByParent(long parent) {
		return this.userDao.getMenuByParent(parent);
	}

	@Transactional
	public List<Menu> getMenuByParent() {
		return this.userDao.getMenuByParent(0);
	}

	@Transactional
	public void AddMenu(Menu menu) {
		this.userDao.AddMenu(menu);
	}

	@Transactional
	public MenuDto getMenuDtoById(long id) {
		return this.userDao.getMenuDtoById(id);
	}

	


}
