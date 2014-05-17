package org.bit.threadtaobao.view;

import org.bit.threadtaobao.client.R;
import org.bit.threadtaobao.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class OrderListView extends Activity {
	private ListView orderListView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);
		init();
	}
	
	public void init() {
		orderListView = (ListView) findViewById(R.id.order_listView);
		String[] array = {"订单1","订单2","订单3"};
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		orderListView.setAdapter(arrayAdapter);
		orderListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				viewItemDetail(position);
			}
			
		});
	}
	
	public void viewItemDetail(int position) {
		View detailView = getLayoutInflater().inflate(R.layout.order_detail, null);
		EditText orderName = (EditText) detailView.findViewById(R.id.order_name);
		String name = (String)orderListView.getAdapter().getItem(position);
		orderName.setText(name);
		DialogUtil.showDialog(OrderListView.this, detailView);
	}
}
