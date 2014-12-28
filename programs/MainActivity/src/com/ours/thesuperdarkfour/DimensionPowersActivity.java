package com.ours.thesuperdarkfour;

import android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DimensionPowersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dimension_powers);
		
		final LinearLayout listDimPowers = (LinearLayout) findViewById(R.id.list_dimensionPowers);
		LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout newSingleRL = new RelativeLayout(this);
		for (int i=0; i<10; ++i) {
			newSingleRL = generateSingleLayout("No."+i+" dynamic list.");
			listDimPowers.addView(newSingleRL,LP_FW);
		}
	}
	
	private RelativeLayout generateSingleLayout(String str) {
		RelativeLayout layout_root_relative = new RelativeLayout(this);
		LinearLayout layout_sub_lin = new LinearLayout(this);
		
		layout_sub_lin.setOrientation(LinearLayout.VERTICAL);
		TextView tView = new TextView(this);
		LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		tView.setText(str);
		tView.setLayoutParams(LP_WW);
		layout_sub_lin.addView(tView);
		
		return layout_root_relative;
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
