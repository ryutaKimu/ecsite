package jp.co.internous.ecsite.model.form;

public class RegisterForm {

	private String userName;
	private String password;
	private String fullName;
	 private int isAdmin;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	   public int getIsAdmin() {
	        return isAdmin;
	    }

	    public void setIsAdmin(int isAdmin) {
	        this.isAdmin = isAdmin;
	    }
	

}

