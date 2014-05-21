package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.util.DialogUtil;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsAdapter extends BaseAdapter{
	private Context context; 
    private ArrayList <Goods> goodsList; 
  
    public GoodsAdapter(Context context, ArrayList<Goods> goodsList) { 
        this.context = context; 
        this.goodsList = goodsList; 
    } 
    
    @Override
    public int getCount(){ 
        // TODO Auto-generated method stub 
        return goodsList.size(); 
    } 
    
    @Override
    public Object getItem(int position) { 
        // TODO Auto-generated method stub 
        return goodsList.get(position); 
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
        View view = convertView; 
        if (view == null) { 
            LayoutInflater inflater = (LayoutInflater) context 
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
            view = inflater.inflate(R.layout.goods_item, null); 
        } 
        final TextView goodsNameTextView = (TextView) view 
                .findViewById(R.id.goodsName);
        final TextView goodsPriceTextView = (TextView) view 
                .findViewById(R.id.goodsPrice);
        goodsNameTextView.setText(goodsList.get(position).getGoodsName());
        goodsPriceTextView.setText(" " + String.valueOf(goodsList.get(position).getGoodsPrice()) + "元");
        
        final Button delButton = (Button) view 
                .findViewById(R.id.delGoods);
        delButton.setTag(position); 
        delButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 GlobalObjects.shoppingCart.delGoods(goodsList.get(index));
//				 goodsList.remove(index);
	             notifyDataSetChanged();
	             Toast.makeText(context, "删除" + goodsNameTextView.getText().toString() + "!", 
	                        Toast.LENGTH_SHORT).show(); 
			}
		});
        
        return view; 
	}
	
}
