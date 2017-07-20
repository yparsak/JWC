package name.parsak.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import name.parsak.dto.MenuDto;
import name.parsak.dto.UsrDptRole;
import name.parsak.model.Department;
import name.parsak.model.Menu;
import name.parsak.model.Role;
import name.parsak.model.User;
import name.parsak.model.UserRole;


public class UserDaoImpl implements UserDao {
	
	private final int first = 0;
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		session.persist(user);
	}
	@Override
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		session.update(user);
	}
	@Override
	public void removeUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);
	}
	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from User where id="+id;
		@SuppressWarnings("unchecked")
		List<User> UserList = session.createQuery(hql).list();
		if (UserList.isEmpty()) {
			return null;
		} else {
			User user = UserList.get(first);
			return user;
		}
	}
	@Override
	public User getUser(long id, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		String hql = "from User where id='"+id+"'";
		@SuppressWarnings("unchecked")
		List<User> UserList = session.createQuery(hql).list();
		if (UserList.isEmpty()) {
			return null;
		}
		else {
			User user = UserList.get(first);
			if (encoder.matches(password, user.getPassword())) {
				return user;
			}
			return null;
		}
	}
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> UserList = session.createQuery("from User").list();
		return UserList;
	}

	@Override
	public Department getDepartmentByName(String deptname) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Department where name='"+deptname+"'";
		@SuppressWarnings("unchecked")
		List<Department> deptlist = session.createQuery(hql).list();
		if (deptlist.isEmpty()) {
			return null;
		} else{ 
			return deptlist.get(first);	
		}
	}	
	@Override
	public void AddDept(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Department department = getDepartmentByName(name); 
		if (department == null) {
			Department dept = new Department();
			dept.setDepartment_name(name);
			session.persist(dept);
		}
	}
	@Override
	public Role getRoleByName(String rolename) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Role where name='"+rolename+"'";
		@SuppressWarnings("unchecked")
		List<Role> rolelist = session.createQuery(hql).list();
		if (rolelist.isEmpty()) { 
			return null; 
		} 
		else {
			return rolelist.get(first);
		}
	}
	@Override
	public void AddRole(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role = getRoleByName(name);
		if (role == null) {
			Role r = new Role ();
			r.setname(name);
			session.persist(r);
		}
	}
	
	@Override
	public UserRole GetUserRole(User user, Department dept, Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where user_id='"+user.getId()+"' and department_id='"+dept.getId()+"' and role_id='"+role.getId()+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userrolelist = session.createQuery(hql).list();
		if (userrolelist.isEmpty()) {
			return null;
		} else {
			return userrolelist.get(first);
		}
	}

	@Override
	public List<UserRole> getUserRoles(Department dept, Role role) {
	Session session = this.sessionFactory.getCurrentSession();
	String hql = "from UserRole where department_id='"+dept.getId()+"' and role_id='"+role.getId()+"'";
	@SuppressWarnings("unchecked")
	List<UserRole> userrolelist = session.createQuery(hql).list();
	return userrolelist;
}
	
	@Override
	public void AddUserRole(User user, Department dept, Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where user_id='"+user.getId()+"' and department_id='"+dept.getId()+"' and role_id='"+role.getId()+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userrolelist = session.createQuery(hql).list();
		if (userrolelist.isEmpty()) {
			UserRole userrole = new UserRole();
			userrole.setUserid(user);
			userrole.setRole(role);
			userrole.setDepartment(dept);
			session.persist(userrole);
		}
	}
	
	@Override
	public List<UsrDptRole> getUsrDptRoles(User user) {
		List<UsrDptRole> result = new ArrayList<UsrDptRole>();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where user_id = '"+user.getId()+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userroles = session.createQuery(hql).list();
		for (int i=0; i< userroles.size(); i++) {
			UserRole userrole = userroles.get(i);
			
			Department dpt = userrole.getDepartment();
			Role       role = userrole.getRole();
			
			UsrDptRole usrdptrole = new UsrDptRole();
			usrdptrole.setId(userrole.getId());
			usrdptrole.setDeptname(dpt.getDepartment_name());
			usrdptrole.setRolename(role.getname());
			usrdptrole.setUserid(user.getId());
			result.add(usrdptrole);
			
		}
		return result;
	}

	@Override
	public void removeUserRoleById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where id = '"+id+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userroles = session.createQuery(hql).list();
		if (userroles.size() > 0) {
			UserRole userrole = userroles.get(first);
			session.delete(userrole);
		}
	}

	@Override
	public List<UsrDptRole> listDepartments() {
		List<UsrDptRole> result = new ArrayList<UsrDptRole>();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Department";
		@SuppressWarnings("unchecked")
		List<Department> dpts = session.createQuery(hql).list();
		for (int i=0; i < dpts.size(); i++) {
			UsrDptRole dptrole = new UsrDptRole();
			dptrole.setDeptname(dpts.get(i).getDepartment_name());
			result.add(dptrole);
		}
		return result;
	}

	@Override
	public List<UsrDptRole> listRoles() {
		List<UsrDptRole> result = new ArrayList<UsrDptRole>();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Role";
		@SuppressWarnings("unchecked")
		List<Role> roles = session.createQuery(hql).list();
		for (int i=0; i < roles.size(); i++) {
			UsrDptRole dptrole = new UsrDptRole();
			dptrole.setRolename(roles.get(i).getname());
			result.add(dptrole);
		}
		return result;
	}

	@Override
	public List<UsrDptRole> getDepartmentUsers(Department department) {
		List<UsrDptRole> result = new ArrayList<UsrDptRole>();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where department_id = '"+department.getId()+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userroles = session.createQuery(hql).list();
		for (int i=0; i< userroles.size(); i++) {
			UserRole userrole = userroles.get(i);
			UsrDptRole usrdptrole = new UsrDptRole();
			usrdptrole.setDeptname(department.getDepartment_name());
			usrdptrole.setRolename(userrole.getRole().getname());
			usrdptrole.setUserid(userrole.getUserid().getId());
			usrdptrole.setUsername(userrole.getUserid().getName());
			usrdptrole.setUserlastname(userrole.getUserid().getLastname());
			result.add(usrdptrole);
		}
		return result;
	}

	// Remove UserRole if user is deleted
	@Override
	public void RemoveUserRoleByUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from UserRole where user_id = '"+user.getId()+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userroles = session.createQuery(hql).list();
		for (int i=0; i< userroles.size(); i++) {
			UserRole userrole = userroles.get(i);
			session.delete(userrole);
		}
	}

	@Override
	public List<Menu> getMenuByParent(long parent) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Menu where parent = '"+parent+"'";
		@SuppressWarnings("unchecked")
		List<Menu> menulist = session.createQuery(hql).list();
		return menulist;
	}

	@Override
	public void AddMenu(Menu menu) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(menu);
	}

	@Override
	public MenuDto getMenuDtoById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Menu where id = '"+id+"'";
		@SuppressWarnings("unchecked")
		List<Menu> menus = session.createQuery(hql).list();
		if (menus.size()> 0) {
			MenuDto result = new MenuDto();
			Menu menu = menus.get(first);
			result.setName(menu.getName());
			result.setApi(menu.getApi());
			return result;
		}
		return null;
	}
}
