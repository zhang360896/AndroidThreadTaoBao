package com.bit.test;

import static org.easymock.EasyMock.createControl;   
import static org.easymock.EasyMock.expect;   
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Discount;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.mainobjects.Order;
import org.bit.threadtaobao.mainobjects.ShoppingCart;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
	private ShoppingCart shoppingCart;
    
    @Before  
    public void init() {  
    	shoppingCart = new ShoppingCart();
    }   
       
    @After  
    public void destory() {   
        shoppingCart = null;  
    }   
  
    @Test  
    public void shoppingCartTest(){   
        
        //创建Mock对象   
        IMocksControl control = createControl(); //创建多个Mock对象时通过IMocksControl管理   
        ArrayList<Goods> goodsList = control.createMock(ArrayList.class);
        ArrayList<Order> orderList = control.createMock(ArrayList.class);
        Goods goods = control.createMock(Goods.class);
        Order order = control.createMock(Order.class);
        Discount discount = control.createMock(Discount.class);
        
        // 录制信息，即设定Mock对象的预期行为和输出  
        
        
        expect(goods.getDiscount()).andReturn(discount).times(1);  
        expect(discount.getDeadline()).andReturn(new Date()).times(1);
        expect(goods.getGoodsPrice()).andReturn(12.5).times(1);  
        expect(discount.getValue()).andReturn(8.0).times(1);
        expect(order.submit()).andReturn(true).times(1);  
        expect(GlobalObjects.orderList).andReturn(orderList).times(1);
        
        //录制完成，切换replay状态   
        control.replay();   
         
        //调用实际的方法  
        shoppingCart.setAllGoodsNum(3);
        shoppingCart.setGoodsList(goodsList);
        shoppingCart.setTotalAmount(13.5);
        boolean res = shoppingCart.generateOrder();   
        boolean expected = true;   
        assertEquals(expected, res);
        
        assertEquals(shoppingCart.getAllGoodsNum(), 3);
        
        //验证   
        control.verify();   
       
    } 
}
