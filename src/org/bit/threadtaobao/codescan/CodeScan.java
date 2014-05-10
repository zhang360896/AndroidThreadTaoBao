package org.bit.threadtaobao.codescan;


import org.bit.threadtaobao.client.R;
import org.bit.threadtaobao.mainobjects.Goods;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
	
	private Goods result;
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
				String[] goodsInfo = mTextView.getText().toString().trim().split(";");
//				Goods goods = new Goods(goodsInfo[0], goodsInfo[1], goodsInfo[2], goodsInfo[3], goodsInfo[5], goodsInfo[4]);
				addToShoppingCartButton.setVisibility(View.INVISIBLE);
				deleteGoodsButton.setVisibility(View.INVISIBLE);
				mTextView.setText("");
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
				mTextView.setText(bundle.getString("result").replace("\n", ";"));
				addToShoppingCartButton.setVisibility(View.VISIBLE);
				deleteGoodsButton.setVisibility(View.VISIBLE);
			}
			break;
		}
    }	

}
