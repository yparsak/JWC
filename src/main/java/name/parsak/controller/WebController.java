package name.parsak.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import name.parsak.api.Tcpip;
import name.parsak.dto.UsrDptRole;
import name.parsak.model.Department;
import name.parsak.model.Menu;
import name.parsak.model.Role;
import name.parsak.model.User;
import name.parsak.service.UserDBService;

@Controller
public class WebController {
	
	final private String project_name = "JWC";
	final private String user_cookie_name = "user";
	final private String blank = "";
	final private int max_login_time = 7200;
	
	private UserDBService userDBService;
	
	@Autowired(required=true)
	@Qualifier(value="UserDBService")
	public void setUserDBService(UserDBService us){
		this.userDBService = us;
	}
	
	// Create Admin User, if it doesn't exist
	@RequestMapping(value="setup")
	public String setup() {
		this.userDBService.setup();
		return "redirect:/";
	}
	
	// Default page
	@RequestMapping({"/", "index"})
	public String index(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid,
			@ModelAttribute(project_name)Menu menu) {

		if (! userid.equals(blank)) {
			
			int id = Integer.parseInt(userid);
			User user = this.userDBService.getUserById(id);
			if (user != null) {
				model.addAttribute("id", userid);
				model.addAttribute("name", user.getName());
				model.addAttribute("lastname", user.getLastname());
				model.addAttribute("email", user.getEmail());
				if (this.userDBService.isUserAdmin(user)) {
					model.addAttribute("admin", "true");
				}
				
				try {
					String name = menu.getName();
					if (name != null) {
						this.userDBService.AddMenu(menu);						
					}
				} catch (NullPointerException e) {}
				
				// menu.id is used to browse
				long menuid = menu.getId();
				model.addAttribute("menu", this.userDBService.getMenuDtoById(menuid));
				model.addAttribute("parent", menuid);
				model.addAttribute("menulist", this.userDBService.getMenuByParent(menuid));
			}
		}
		return "index";
	}
	
	// Login User
	@RequestMapping(value="login")
	public String login(
			Model model, @ModelAttribute(project_name)User u,
			HttpServletResponse response) {

		Long userid = u.getId();
		// In case user enters /login without submitting a login form
		if (userid != 0) {
			User user = this.userDBService.getUser(u.getId(), u.getPassword());
			if (user != null) {
				Cookie cookie = new Cookie(user_cookie_name, String.valueOf(user.getId()));
				cookie.setMaxAge(max_login_time);
				response.addCookie(cookie);
			}			
		}
		return "redirect:/";
	}

	// Logout User
	@RequestMapping(value="logout")
	public String logout(
			HttpServletResponse response) {

		Cookie cookie = new Cookie(user_cookie_name,blank);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	// Display All Users
	@RequestMapping(value="users")
	public String users(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid) {

		if (! userid.equals(blank)) {
			int adminid = Integer.parseInt(userid);
			User admin = this.userDBService.getUserById(adminid);
			if (admin != null) {
				if (this.userDBService.isUserAdmin(admin)) {

					model.addAttribute("id", userid);
					model.addAttribute("admin", "true");
					model.addAttribute("userslist", this.userDBService.listUsers());
					return "users";
				}
			}
		}
		return "redirect:/";
	}
	
	// Display All Departments
	@RequestMapping(value="departments")
	public String departments(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid) {

		if (! userid.equals(blank)) {
			int adminid = Integer.parseInt(userid);
			User admin = this.userDBService.getUserById(adminid);
			if (admin != null) {
				if (this.userDBService.isUserAdmin(admin)) {

					model.addAttribute("id", userid);
					model.addAttribute("admin", "true");

					model.addAttribute("deptlist", this.userDBService.listDepartments());
					model.addAttribute("rolelist", this.userDBService.listRoles());
					return "departments";
				}
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="department")
	public String department(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid, 
			@ModelAttribute(project_name)UsrDptRole usrdptrole){
		
		if (! userid.equals(blank)) {
			int adminid = Integer.parseInt(userid);
			User admin = this.userDBService.getUserById(adminid);
			if (admin != null) {
				if (this.userDBService.isUserAdmin(admin)) {

					model.addAttribute("id", userid);
					model.addAttribute("admin", "true");

					String DeptName = usrdptrole.getDeptname();
					
					System.out.println(">> Displaying "+DeptName);
					Department department = this.userDBService.getDepartmentByName(DeptName);
					model.addAttribute("department", department.getDepartment_name());
					//
					model.addAttribute("deptrolelist", this.userDBService.getDepartmentUsers(department));
					return "department";
				}
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="add_user")
	public String add_user(
			Model model, 
			@ModelAttribute(project_name)User user,
			HttpServletResponse response) {
		
		String user_name     = null;
		String user_lastname = null;
		String user_email 	 = null;
		String user_password = null;
		try {
			user_name = user.getName();
			user_lastname = user.getLastname();
			user_email = user.getEmail();
			user_password = user.getPassword();
		} catch (NullPointerException e) {}

		if (user_name != null && user_lastname != null && user_email != null && user_password != null) {
			this.userDBService.addUser(user);
		}
		return "redirect:/users";
	}
	
	@RequestMapping(value="update_user")
	public String update_user(
			Model model, 
			@ModelAttribute(project_name)User user,
			HttpServletResponse response) {

		String user_name     = null;
		String user_lastname = null;
		String user_email 	 = null;
		String user_password = null;
		try {
			user_name = user.getName();
			user_lastname = user.getLastname();
			user_email = user.getEmail();
			user_password = user.getPassword();
		} catch (NullPointerException e) {}

		if (user_name != null && user_lastname != null && user_email != null && user_password != null) {
			this.userDBService.updateUser(user);
		}
		return "redirect:/user"+user.getId();
	}
	
	// Assign UserRole to a user
	@RequestMapping(value="add_user_role")
	public String AddUserRole(@ModelAttribute(project_name)UsrDptRole usrdptrole) {	

		String deptname = usrdptrole.getDeptname();
		String rolename = usrdptrole.getRolename();
		long userid = usrdptrole.getUserid();

		if (userid != 0 && !deptname.equals(blank) && !rolename.equals(blank)) {
			User user = this.userDBService.getUserById((int) userid);
			Department department = this.userDBService.getDepartmentByName(deptname);
			Role       role       = this.userDBService.getRoleByName(rolename);
			this.userDBService.AddUserRole(user, department, role);
		}
		return "redirect:/user"+userid;
	}
	
	@RequestMapping(value="remove_userrole")
	public String RemoveUserRole(@ModelAttribute(project_name) UsrDptRole deptrole) {	
		this.userDBService.removeUserRoleById(deptrole.getId());
		return "redirect:/user"+deptrole.getUserid();
	}
	
	// Display User{id}
	@RequestMapping(value="/user{id}")
	public String user(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid, 
			@PathVariable("id") int id) {

		if (! userid.equals(blank)) {
			int adminid = Integer.parseInt(userid);
			User admin = this.userDBService.getUserById(adminid);
			if (admin != null) {
				if (this.userDBService.isUserAdmin(admin)) {
					model.addAttribute("id", userid);
					model.addAttribute("admin", "true");
					User user = this.userDBService.getUserById(id);
					model.addAttribute("userid",user.getId());
					model.addAttribute("name",user.getName());
					model.addAttribute("lastname",user.getLastname());
					model.addAttribute("email",user.getEmail());
					model.addAttribute("User", user); // To populate Edit Form
					model.addAttribute("deptrolelist",this.userDBService.getUsrDptRoles(user));
					model.addAttribute("deptlist", this.userDBService.listDepartments());
					model.addAttribute("rolelist", this.userDBService.listRoles());
					return "user";
				}
			}
		}
		return "redirect:/";
	}	
	
	// Remove User{id}
	@RequestMapping(value="/removeuser{id}")
	public String removeuser(
			Model model, 
			@CookieValue(value = user_cookie_name, defaultValue = blank) String userid, 
			@PathVariable("id") int id) {

		if (! userid.equals(blank)) {
			int adminid = Integer.parseInt(userid);
			User admin = this.userDBService.getUserById(adminid);
			if (admin != null) {
				if (this.userDBService.isUserAdmin(admin)) {
					model.addAttribute("id", userid);
					model.addAttribute("admin", "true");
					User user = this.userDBService.getUserById(id);	
					this.userDBService.RemoveUserRoleByUser(user);
					this.userDBService.removeUser(user);
					return "redirect:/users";
				}
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/add_department")
	public String addNewDepartment(@ModelAttribute(project_name) UsrDptRole deptrole) {
		this.userDBService.AddDepartment(deptrole.getDeptname());
		return "redirect:/departments";
	}

	@RequestMapping(value="/add_role")
	public String addNewRole(@ModelAttribute(project_name) UsrDptRole deptrole) {
		this.userDBService.AddRole(deptrole.getRolename());
		return "redirect:/departments";
	}
	
	@RequestMapping(value="/api_test")
	public String test_api(Model model) {
		Tcpip tcpip = new Tcpip();
		String response = tcpip.get("http://www.google.com");
		model.addAttribute("response", response);
		return "api_test";
	}
}