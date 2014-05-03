package org.bit.threadtaobao.view;

import org.bit.threadtaobao.client.R;
import org.bit.threadtaobao.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ShoppingCartView extends Activity {

	private ListView shoppingcartListView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart);
		init();
	}
	//初始化界面
	public void init() {
		shoppingcartListView = (ListView) findViewById(R.id.shoppingcart_listView);
		String[] array = {"商品1","商品2","商品3"};
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		shoppingcartListView.setAdapter(arrayAdapter);
		
		shoppingcartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				viewItemDetail(position);
			}
			
		});
	}
	
	public void viewItemDetail(int position) {
		View detailView = getLayoutInflater().inflate(R.layout.product_detail, null);
		EditText productName = (EditText) detailView.findViewById(R.id.product_name);
		String name = (String)shoppingcartListView.getAdapter().getItem(position);
		productName.setText(name);
		DialogUtil.showDialog(ShoppingCartView.this, detailView);
	}
}
