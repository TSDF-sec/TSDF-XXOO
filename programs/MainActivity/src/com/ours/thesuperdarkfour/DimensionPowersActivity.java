package com.ours.thesuperdarkfour;

import android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DimensionPowersActivity extends Activity {
	private static final String tag = "junfeng-debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(tag, "I'm in DimensionPowersActivity's onCreate()!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dimension_powers);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dimension_powers, menu);
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
