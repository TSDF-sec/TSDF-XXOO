package com.ours.thesuperdarkfour;

import com.ours.thesuperdarkfour.R.string;
import com.ours.thesuperdarkfour.User.Gender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//处理性别的下拉菜单
		Spinner spinner_userGender = (Spinner) findViewById(R.id.spinner_UserGender);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_userGender.setAdapter(adapter);
		
	}
	
	public void doneRegister(View view) {
		//从UI读取注册信息
		EditText editText_userName = (EditText) findViewById(R.id.editText_UserName);
		String newUserName = editText_userName.getText().toString();
		EditText editText_userAge = (EditText) findViewById(R.id.editText_UserAge);
		int newUserAge = Integer.valueOf(editText_userAge.getText().toString());
		Spinner spinner_userGender = (Spinner) findViewById(R.id.spinner_UserGender);
		Gender newUserGender;
		switch(spinner_userGender.getSelectedItem().toString()) {
			case "爷们":
				newUserGender = Gender.MALE;
			case "妹子":
				newUserGender = Gender.FEMALE;
			case "3rd":
				newUserGender = Gender.SHEMALE;
			default:
				newUserGender = Gender.MALE;
		}
		
		CheckBox checkBox_D1 = (CheckBox) findViewById(R.id.checkBox_D1);
		CheckBox checkBox_D2 = (CheckBox) findViewById(R.id.checkBox_D2);
		CheckBox checkBox_D3 = (CheckBox) findViewById(R.id.checkBox_D3);
		CheckBox checkBox_D4 = (CheckBox) findViewById(R.id.checkBox_D4);
		CheckBox checkBox_D5 = (CheckBox) findViewById(R.id.checkBox_D5);
		boolean newUserFocus[] = new boolean[5];
		newUserFocus[0] = checkBox_D1.isChecked();
		newUserFocus[1] = checkBox_D2.isChecked();
		newUserFocus[2] = checkBox_D3.isChecked();
		newUserFocus[3] = checkBox_D4.isChecked();
		newUserFocus[4] = checkBox_D5.isChecked();
		
		//将信息保存至文件
		Logic.getLogic().createNewUser(222, newUserName, newUserAge, newUserGender);
		Logic.getLogic().loadUserInfo();
		System.out.println(Logic.getLogic().getUser().userName);
		System.out.println(Logic.getLogic().getUser().userAge);
		
		//反回主界面
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
