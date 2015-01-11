package com.ours.thesuperdarkfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DimensionsActivity extends Activity {
	private static final String tag = "junfeng-debug";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(tag, "I'm in DimensionsActivity's onCreate()!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dimensions);
	}
	
	public void showPowers(View view) {
		Log.d(tag, "call showPowers()");
		Intent dimPowIntent  = new Intent(this, DimensionPowersActivity.class);
		startActivity(dimPowIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dimensions, menu);
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
