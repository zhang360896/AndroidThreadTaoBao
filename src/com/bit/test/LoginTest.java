package com.bit.test;

import static org.easymock.EasyMock.createControl;   
import static org.easymock.EasyMock.expect;   
import static org.junit.Assert.assertEquals;

import org.bit.threadtaobao.mainobjects.User;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginTest {
	private User user;   
    
    @Before  
    public void init() {   
        user= new User("admin","admin");   
    }   
       
    @After  
    public void destory() {   
        user = null;   
    }   
  
    @Test  
    public void login(){   
        
        //创建Mock对象   
        IMocksControl control = createControl(); //创建多个Mock对象时通过IMocksControl管理   
  
        SQLiteDatabase db = control.createMock(SQLiteDatabase.class);   
        Cursor cursor = control.createMock(Cursor.class);   
        
        
        // 录制信息，即设定Mock对象的预期行为和输出  
        String sql = "select * from users where username = ? and password = ?";
        
        expect(db.rawQuery(sql, new String[] { user.getUsername(), user.getPassword() })).andReturn(cursor).times(1);  
        expect(cursor.getCount()).andReturn(1);
        
        db.close();
        cursor.close();
        //录制完成，切换replay状态   
        control.replay();   
         
        //调用实际的方法   
        boolean res = user.login();   
        boolean expected = true;   
        assertEquals(expected, res);      
        //验证   
        control.verify();   
       
    } 
}
