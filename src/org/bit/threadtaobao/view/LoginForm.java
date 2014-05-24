package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.mainobjects.Order;
import org.bit.threadtaobao.mainobjects.ShoppingCart;
import org.bit.threadtaobao.mainobjects.User;
import org.bit.threadtaobao.util.ConfigureLog4J;
import org.bit.threadtaobao.util.DialogUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author jmm
 *
 */
public class LoginForm extends Activity
{
	// 定义界面中两个文本框
	EditText etName, etPass;
	// 定义界面中两个按钮
	Button bnLogin, bnCancel, bnRegister;
	public static SQLiteDatabase db;
	//日志
	private Logger logger; 
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ConfigureLog4J.configure();
		logger = Logger.getLogger(LoginForm.class);
		// open sqlite database
		db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
				.toString() + "/thread_db.db3", null);
		// 获取界面中两个编辑框
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// 获取界面中的两个按钮
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		bnRegister = (Button) findViewById(R.id.bnRegister);
		// 为bnCancal按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				confirmExit();
			}
		});
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				//用户名密码非空
				if (validateNotNull()) {
					//用户名密码正确
					if(validateLogin()) {
						logger.trace("登录成功！");
						GlobalObjects.shoppingCart = new ShoppingCart(new ArrayList<Goods>(),0,0);
						GlobalObjects.orderList = new ArrayList<Order>();
						// 启动Main Activity
						Intent intent = new Intent(LoginForm.this, MainForm.class);
						startActivity(intent);
						// 结束该Activity
						finish();
					}
					else {
						logger.trace("登录失败！用户名称或者密码错误！");
						DialogUtil.showDialog(LoginForm.this
							, "用户名称或者密码错误，请重新输入！", false);
					}
				}
			}
		});
		
		bnRegister.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				//用户名密码非空
				if (validateNotNull()) {
					//用户名是否已存在
					if(validateRegister()) {
						logger.trace("注册成功！");
						DialogUtil.showDialog(LoginForm.this
								, "注册成功！", false);
					}
					else {
						logger.trace("用户名已存在!");
						DialogUtil.showDialog(LoginForm.this
							, "用户名已存在，请重新输入！", false);
					}
				}
			}
		});
	}

	//对用户名和密码进行校验
	private boolean validateNotNull()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "用户名是必填项！", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "密码是必填项！", false);
			return false;
		}
		return true;
	}
	
	//校验用户名密码
	private boolean validateRegister()
	{
		String username = etName.getText().toString().trim();
		String pwd = etPass.getText().toString().trim();
		User user = new User(username,pwd);
		
		if (user.register()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	//校验用户名密码
	private boolean validateLogin()
	{
		String username = etName.getText().toString().trim();
		String pwd = etPass.getText().toString().trim();
		GlobalObjects.currentUser = new User(username,pwd);
		
		if (GlobalObjects.currentUser.login()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	private void confirmExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(LoginForm.this);
		builder.setTitle("警告").setMessage("您确定要退出吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						LoginForm.this.finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show(); 
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			confirmExit();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null && db.isOpen()) {
			db.close();
		}
		logger.trace("退出");
	}
}