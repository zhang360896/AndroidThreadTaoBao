package org.bit.threadtaobao.mainobjects;

import org.bit.threadtaobao.view.LoginForm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

	public User(String username, String password) {
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

	public String getUsername() {
		return username;
	}
	
	private void modifyPassword() {
		
	}
	public boolean login() {
		SQLiteDatabase db = LoginForm.db;
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(
				"select * from users where username = ? and password = ?",
				new String[] { username, password });
		} catch (Exception e) {
			// TODO: handle exception
			//执行DDL创建数据表
			db.execSQL("create table users(userid integer primary key autoincrement,"
				+ " username varchar(20),"
				+ " password varchar(20))");
			return false;
		}
		
		if (cursor.getCount() > 0) {
			return true;
		}
		return false;
	}

	public boolean logout() {
		return true;
	}

	public boolean register() {
		SQLiteDatabase db = LoginForm.db;
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(
				"select * from users where username = ?",
				new String[] { username });
		} catch (Exception e) {
			// TODO: handle exception
			//执行DDL创建数据表
			db.execSQL("create table users(userid integer primary key autoincrement,"
				+ " username varchar(20),"
				+ " password varchar(20))");
			//执行插入语句
			db.execSQL("insert into users values(null , ? , ?)"
				, new String[]{ username, password});
			return true;
		}
		
		if (cursor.getCount() > 0) {
			return false;
		} else {
			db.execSQL("insert into users values(null , ? , ?)"
					, new String[]{ username, password});
			return true;
		}

	}

	public void viewShoppingCart() {
		
	}

	public void viewOrders() {

	}

	public void viewLocation() {

	}

}
