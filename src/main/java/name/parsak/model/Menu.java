package name.parsak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Menu")
public class Menu {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private String api;
	private long parent;
	
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getApi() {return api;}
	public void setApi(String api) {this.api = api;}
	public long getParent() {return parent;}
	public void setParent(long parent) {this.parent = parent;}

}
