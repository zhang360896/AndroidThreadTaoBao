package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class ShoppingCartView extends Activity {

	private ListView shoppingcartListView = null;
	private ArrayList<Goods> goodsList;
	private EditText allAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart);
		init();
	}

	// 初始化界面
	public void init() {
		shoppingcartListView = (ListView) findViewById(R.id.shoppingcart_listView);
		goodsList = GlobalObjects.shoppingCart.getGoodsList();

		GoodsAdapter adapter = new GoodsAdapter(this, goodsList);
		shoppingcartListView.setAdapter(adapter);

		shoppingcartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				viewItemDetail(position);
			}
		});

		allAmount = (EditText) findViewById(R.id.all_amount);
		allAmount.setText("共："
				+ String.valueOf(GlobalObjects.shoppingCart.getAllGoodsNum())
				+ "件商品 ，  "
				+ " 总价： " + String.valueOf(GlobalObjects.shoppingCart.getTotalAmount()) + "元");
	}

	public void viewItemDetail(int position) {
		View detailView = getLayoutInflater().inflate(R.layout.goods_detail,
				null);
		Goods goods = (Goods) (shoppingcartListView.getAdapter()
				.getItem(position));

		EditText goodsName = (EditText) detailView
				.findViewById(R.id.goods_name);
		goodsName.setText(goods.getGoodsName());

		EditText goodsBrand = (EditText) detailView
				.findViewById(R.id.goods_brand);
		goodsBrand.setText(goods.getGoodsBrand());

		EditText goodsPrice = (EditText) detailView
				.findViewById(R.id.goods_price);
		goodsPrice.setText(String.valueOf(goods.getGoodsPrice()) + "元");

		EditText supermarket = (EditText) detailView
				.findViewById(R.id.supermarket);
		supermarket.setText(goods.getSupermarket().getName());

		EditText discount = (EditText) detailView.findViewById(R.id.discount);
		if (goods.getDiscount() == null) {
			discount.setText("无");
		} else {
			discount.setText(String.valueOf(goods.getDiscount().getValue()) + "折");
		}
		
		DialogUtil.showDialog(ShoppingCartView.this, detailView);
	}
}
