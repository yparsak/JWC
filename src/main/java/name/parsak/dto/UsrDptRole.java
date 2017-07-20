
// Data Transfer Object
package name.parsak.dto;

public class UsrDptRole {
	
	private long id;
	private long userid;
	private String deptname;
	private String rolename;
	
	private String username;
	private String userlastname;

	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	public long getUserid() {return userid;}
	public void setUserid(long userid) {this.userid = userid;}
	public String getDeptname() {return deptname;}
	public void setDeptname(String deptname) {this.deptname = deptname;}
	public String getRolename() {return rolename;}
	public void setRolename(String rolename) {this.rolename = rolename;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getUserlastname() {return userlastname;}
	public void setUserlastname(String userlastname) {this.userlastname = userlastname;}
	
}
