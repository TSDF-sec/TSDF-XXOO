package com.ours.thesuperdarkfour;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String tag = "junfeng-debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(tag, "I'm in MainActivity's onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			Logic logic = new Logic();
			Logic.setInitLogic(logic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Boolean userExist = Logic.getLogic().loadUserInfo(this);
		
		if (userExist) {
		TextView textView_welcome = (TextView) findViewById(R.id.textView_Welcome);
		textView_welcome.setText("»¶Ó­£¬" + Logic.getLogic().getUser().userName );
		}
	}
    
	public void toPower(View view) {
		Intent intent = new Intent(this, PlanDesignerActivity.class);
	    startActivity(intent);
	}
	
	public void toUser(View view) {
		Intent intent = new Intent(this, UserInfoActivity.class);
	    startActivity(intent);
	}
	
	public void register(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
	    startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void showDimensions(View view) {
		Log.d(tag, "call showDimensions()");
		Intent dimIntent  = new Intent(this, DimensionsActivity.class);
		startActivity(dimIntent);
	}
}
