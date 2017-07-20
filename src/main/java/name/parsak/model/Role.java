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
@Table(name="Role")
public class Role {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="user_id")
	private List<UserRole> user_roles;
	
	private String name;
	
	public String getname() {return name;}
	public void setname(String name) {this.name = name;}

	public List<UserRole> getUser_roles() {return user_roles;}
	public void setUser_roles(List<UserRole> user_roles) {this.user_roles = user_roles;}

	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
}
