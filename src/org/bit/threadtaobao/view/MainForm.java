package org.bit.threadtaobao.view;


import org.bit.threadtaobao.codescan.CodeScan;
import org.bit.threadtaobao.location.MyLocation;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
public class MainForm extends Activity
{
	ListView mainMenu;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainMenu = (ListView) findViewById(R.id.mainMenu);
		// 为ListView的各列表项的单击事件绑定事件监听器。
		mainMenu.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				Intent intent = null;
				switch ((int) id)
				{
					// 查看个人信息
					case 0:
						// 启动UserProfile Activity
						intent = new Intent(MainForm.this, UserProfile.class);
						startActivity(intent);
						break;
					// 扫一扫
					case 1:
						// 启动TwoDimenCodeScan Activity
						intent = new Intent(MainForm.this, CodeScan.class);
						startActivity(intent);
						break;
					// 我的购物车
					case 2:
						// 启动ViewShoppingCart Activity
						intent = new Intent(MainForm.this, ShoppingCartView.class);
						startActivity(intent);
						break;
					// 查看我的订单
					case 3:
						// 启动ViewOrder Activity
						intent = new Intent(MainForm.this, OrderListView.class);
						startActivity(intent);
						break;
					// 查看我的位置
					case 4:
						// 启动MyLocation Activity
						intent = new Intent(MainForm.this, MyLocation.class);
						startActivity(intent);
//						Alipay alipay = new Alipay(MainForm.this);
//						alipay.alipay("iphone5s", "iphone5s 土豪金", "5800");
						break;
					// 退出登录
					case 5:
						// 启动ChooseKind Activity
						intent = new Intent(MainForm.this, LoginForm.class);
						startActivity(intent);
						finish();
						break;
				}
			}
		});
	}
}