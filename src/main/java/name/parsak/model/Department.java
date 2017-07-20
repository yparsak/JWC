package name.parsak.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Department {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy="user_id")
	private List<UserRole> user_roles;
	
	private String name;

	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public List<UserRole> getUserRoles() {return user_roles;}
	public void setUserRoles(List<UserRole> user_roles) {this.user_roles= user_roles;}

	public String getDepartment_name() {return name;}
	public void setDepartment_name(String name) {this.name = name;}

}
