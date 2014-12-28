package com.ours.thesuperdarkfour;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.CheckBox;

public class PlanDesignerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_designer);
		
		Logic logic = Logic.getLogic();
		ScrollView sView = (ScrollView)findViewById(R.id.powerchooseView);
		ArrayList<Power> pws = logic.powerdb.getAllPowers();
		for (Iterator<Power> iter = pws.iterator(); iter.hasNext();) {
			Power p = iter.next();
			CheckBox btn = new CheckBox(this);
			btn.setId(p.powerID);
			btn.setText(p.powerName+"\n"+p.powerDescription);
			sView.addView(btn);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plan_designer, menu);
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
	
	public void onclickClose( View view ) {
		this.onDestroy();
	}
}
