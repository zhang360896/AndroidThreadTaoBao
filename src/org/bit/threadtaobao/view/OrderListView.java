package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.mainobjects.Order;
import org.bit.threadtaobao.util.ConfigureLog4J;
import org.bit.threadtaobao.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class OrderListView extends Activity {
	private ListView orderListView;
	private ArrayList<Order> orderList;
	//日志
	private Logger logger; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);
		ConfigureLog4J.configure();
		logger = Logger.getLogger(OrderListView.class);
		init();
	}
	
	public void init() {
		orderListView = (ListView) findViewById(R.id.order_listView);

		orderList = GlobalObjects.orderList;

		OrderAdapter adapter = new OrderAdapter(this, orderList);
		orderListView.setAdapter(adapter);
		orderListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				logger.trace("查看订单详情");
				viewItemDetail(position);
			}
			
		});
	}
	
	public void viewItemDetail(int position) {
		View detailView = getLayoutInflater().inflate(R.layout.order_detail, null);
		
		Order order = (Order) orderListView.getAdapter().getItem(position);
		
		TextView orderId = (TextView) detailView
				.findViewById(R.id.order_id);
		orderId.setText(order.getOrderId());

		TextView goodsName = (TextView) detailView
				.findViewById(R.id.order_goods);
		StringBuffer stringBuffer = new StringBuffer();
		for (Goods goods : order.getGoodsList()) {
			stringBuffer.append(goods.getGoodsName() + " ");
		}
		goodsName.setText(stringBuffer.toString());

		TextView orderPrice = (TextView) detailView
				.findViewById(R.id.order_price);
		orderPrice.setText(String.valueOf(order.getTotalAmount()) + "元");

		TextView orderState = (TextView) detailView
				.findViewById(R.id.order_detail_state);
		orderState.setText(order.getState());

		DialogUtil.showDialog(OrderListView.this, detailView);
	}
}
