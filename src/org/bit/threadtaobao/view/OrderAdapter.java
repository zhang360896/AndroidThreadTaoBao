package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.bit.threadtaobao.codescan.CodeScan;
import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.mainobjects.Order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderAdapter extends BaseAdapter {
	private Context context; 
    private ArrayList <Order> orderList;
    
	public OrderAdapter(Context context, ArrayList<Order> orderList) {
		super();
		this.context = context;
		this.orderList = orderList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int index = position;
		final Order order = orderList.get(index);
        View view = convertView; 
        if (view == null) { 
            LayoutInflater inflater = (LayoutInflater) context 
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
            view = inflater.inflate(R.layout.order_item, null); 
        } 
        final TextView goodsNameTextView = (TextView) view 
                .findViewById(R.id.order_goods_name);
        final TextView goodsPriceTextView = (TextView) view 
                .findViewById(R.id.order_all_amount);
        final TextView orderStateTextView = (TextView) view 
                .findViewById(R.id.order_state);
        ArrayList<Goods> goodsList = order.getGoodsList();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(goodsList.get(0).getGoodsName() + "……");
        goodsNameTextView.setText(stringBuffer.toString());

        goodsPriceTextView.setText(" " + String.valueOf(order.getTotalAmount()) + "元");
        
        orderStateTextView.setText(order.getState());
        
        final Button payOrderButton = (Button) view 
                .findViewById(R.id.pay_order);
        if (order.getState().equals("已支付")) {
			payOrderButton.setVisibility(View.INVISIBLE);
		}
        payOrderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("提醒").setMessage("确定支付：" + String.valueOf(order.getTotalAmount()) + "元？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								if (order.pay()) {
									notifyDataSetChanged();
									Toast.makeText(context, "支付成功！", 
				                        Toast.LENGTH_SHORT).show();
									payOrderButton.setVisibility(View.INVISIBLE);
								}			
							}
						})
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						}).show();
			}
		});
        
        final Button delButton = (Button) view 
                .findViewById(R.id.delOrder);
        delButton.setTag(position); 
        delButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 GlobalObjects.orderList.remove(index);
	             notifyDataSetChanged();
	             Toast.makeText(context, "删除订单!", 
	                        Toast.LENGTH_SHORT).show(); 
			}
		});
        
        return view; 
	}

}
