package org.bit.threadtaobao.view;

import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class UserProfile extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);
		User currentUser = GlobalObjects.currentUser;
		EditText userName = (EditText) findViewById(R.id.userName);
		EditText realName = (EditText) findViewById(R.id.realName);
		userName.setText(currentUser.getUsername());
		realName.setText(currentUser.getUsername());
	}

}
