package name.parsak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="UserRole")
public class UserRole {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	private Role role_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department_id;	
	
	public long getId() { return id;}
	
	public User getUserid() {return user_id;}
	public void setUserid(User user) {this.user_id = user;}
	
	public User getUser_id() {return user_id;}
	public void setUser_id(User user) {this.user_id = user;}

	public Role getRole() {return role_id;}
	public void setRole(Role role) {this.role_id = role;}

	public Department getDepartment() {return department_id;}
	public void setDepartment(Department department_id) {this.department_id = department_id;}
	
}
