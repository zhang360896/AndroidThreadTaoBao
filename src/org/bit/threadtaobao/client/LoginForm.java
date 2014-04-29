package org.bit.threadtaobao.client;

import org.bit.threadtaobao.client.util.DialogUtil;
import org.bit.threadtaobao.client.util.FinishListener;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class LoginForm extends Activity
{
	// 定义界面中两个文本框
	EditText etName, etPass;
	// 定义界面中两个按钮
	Button bnLogin, bnCancel;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// 获取界面中两个编辑框
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// 获取界面中的两个按钮
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		// 为bnCancal按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new FinishListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				if (validate())
				{
					// 如果登录成功
//					if (loginPro())
					{
						// 启动Main Activity
						Intent intent = new Intent(LoginForm.this, MainForm.class);
						startActivity(intent);
						// 结束该Activity
						finish();
					}
//					else
//					{
//						DialogUtil.showDialog(Login.this
//							, "用户名称或者密码错误，请重新输入！", false);
//					}
				}
			}
		});
	}

//	private boolean loginPro()
//	{
//		// ��ȡ�û�������û�������
//		String username = etName.getText().toString();
//		String pwd = etPass.getText().toString();
//		JSONObject jsonObj;
//		try
//		{
//			jsonObj = query(username, pwd);
//			// ���userId ����0
//			if (jsonObj.getInt("userId") > 0)
//			{
//				return true;
//			}
//		}
//		catch (Exception e)
//		{
//			DialogUtil.showDialog(this, "��������Ӧ�쳣�����Ժ����ԣ�", false);
//			e.printStackTrace();
//		}
//
//		return false;
//	}

	// ���û�������û����������У��
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��˻��Ǳ����", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
			return false;
		}
		return true;
	}
	
	private void confirmExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(LoginForm.this);
		builder.setTitle("�˳����").setMessage("�Ƿ��˳����?")
				.setPositiveButton("��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						LoginForm.this.finish();
					}
				})
				.setNegativeButton("��", new DialogInterface.OnClickListener() {
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

}