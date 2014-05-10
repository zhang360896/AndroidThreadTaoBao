package org.bit.threadtaobao.mainobjects;

public class User {
	private String userId;
	private String password;
	private String username;
	
	public User(String userId, String password, String username) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
	}
	
	public User( String username, String password) {
		super();
		this.password = password;
		this.username = username;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
	private void modifyPassword(){
		
	}
	
	public String getUsername() {
		return username;
	}

	public boolean login(){
		if (username.equals("admin") && password.equals("admin")) {
			return true;
		}
		return false;
	}
	
	public void logout(){
		
	}
	
	public void register(){
		
	}
	
	public void viewShoppingCart(){
		
	}
	
	public void viewOrders(){
		
	}
	
	public void viewLocation(){
		
	}
	
}
