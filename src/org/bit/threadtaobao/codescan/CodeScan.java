package org.bit.threadtaobao.codescan;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Discount;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.mainobjects.Supermarket;
import org.bit.threadtaobao.view.LoginForm;
import org.bit.threadtaobao.view.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CodeScan extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * 显示扫描结果
	 */
	private TextView mTextView ;
	
	private Goods goods;
	private Button mButton;
	private Button addToShoppingCartButton;
	private Button deleteGoodsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.code_scan);
		
		mTextView = (TextView) findViewById(R.id.result);
		
		//点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
		//扫描完了之后调到该界面
		mButton = (Button) findViewById(R.id.code_scan_button);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(CodeScan.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
		
		addToShoppingCartButton = (Button) findViewById(R.id.addToShoppingCart);
		addToShoppingCartButton.setVisibility(View.INVISIBLE);
		addToShoppingCartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(CodeScan.this);
				builder.setTitle("提醒").setMessage("确定添加该商品到购物车？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								GlobalObjects.shoppingCart.addGoods(goods);
								addToShoppingCartButton.setVisibility(View.INVISIBLE);
								deleteGoodsButton.setVisibility(View.INVISIBLE);
								mTextView.setText("");
							}
						})
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						}).show(); 		
			}
		});
		
		
		deleteGoodsButton = (Button) findViewById(R.id.deleteGoods);
		deleteGoodsButton.setVisibility(View.INVISIBLE);
		deleteGoodsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addToShoppingCartButton.setVisibility(View.INVISIBLE);
				deleteGoodsButton.setVisibility(View.INVISIBLE);
				mTextView.setText("");
			}
		});
		
		
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				//显示扫描到的商品内容
				mTextView.setText(bundle.getString("result"));
				addToShoppingCartButton.setVisibility(View.VISIBLE);
				deleteGoodsButton.setVisibility(View.VISIBLE);
				//构造商品对象
				String[] result = bundle.getString("result").split("\n");
				Log.d("codesscan", result[0]);
				String goodsId = result[0].split("：")[1];
				String goodsName = result[1].split("：")[1];
				String goodsBrand = result[2].split("：")[1];
				String goodsPriceString = result[3].split("：")[1];
				double goodsPrice = Double.valueOf(goodsPriceString.substring(0, goodsPriceString.length()-1));
				Discount discount = null;
				String discountString = result[5].split("：")[1];
				if (discountString.equals("无")) {
					discount = null;
				} else {
					String discountValueString = discountString.split("，")[0];
					String discountDeadlineString = discountString.split("，")[1];
					try{
						discount = new Discount(Double.valueOf(discountValueString.substring(0, discountValueString.length()-1)), new SimpleDateFormat("yyyy/MM/dd").parse(discountDeadlineString));
					} catch(ParseException e){
						e.printStackTrace();
					}
				}
				String sm = result[4].split("：")[1];
				Supermarket supermarket = new Supermarket(sm.split("，")[0], sm.split("，")[1]);
				goods = new Goods(goodsId,goodsName,goodsBrand,goodsPrice,discount,supermarket);				
			}
			break;
		}
    }	

}
