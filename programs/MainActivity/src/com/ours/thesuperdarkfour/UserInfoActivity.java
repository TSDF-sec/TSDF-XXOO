package com.ours.thesuperdarkfour;

import java.io.IOException;
import java.util.ArrayList;

import com.ours.thesuperdarkfour.User.Gender;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserInfoActivity extends Activity {
	public static final String debugtag = "debugtag";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		
		Logic logic = Logic.getLogic();
		//logic.createNewUser(222, "¥Û÷µ…Ò", 88, Gender.MALE);
		logic.loadUserInfo();
		Power p = new Power();
		p.powerName = "Attend a conference";
		logic.powerdb.addPower(p);
		logic.powerdb.addPower(p);
		p = new Power();
		p.powerName = "Hold a conference";
		logic.powerdb.addPower(p);
		p = new Power();
		p.powerName = "Attend or hold 4 conferences";
		logic.powerdb.addPower(p);
		try {
			logic.powerdb.savePower();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Power> powers = logic.powerdb.getAllPowers();
		Log.d(debugtag, Integer.toString(powers.size()));
		
		TextView textView1 = (TextView)findViewById(R.id.textView_Welcome);
		String toSet = "ª∂”≠£¨" + logic.getUser().userName;
		textView1.setText(toSet);
		
		LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout0);
		for (int i=0;i<powers.size();i++) {
			TextView tvtmp = new TextView(this);
			tvtmp.setHeight(40);
			tvtmp.setText(powers.get(i).powerName);
			tvtmp.setTextSize(20);
			tvtmp.setTextColor(Color.rgb(255, 0, 0));
			tvtmp.setGravity(Gravity.LEFT);
			linearLayout1.addView(tvtmp);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_info, menu);
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
