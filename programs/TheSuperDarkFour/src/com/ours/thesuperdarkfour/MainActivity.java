package com.ours.thesuperdarkfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private TextView tv1 = null;
	private Button bt1 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        this.tv1=(TextView)super.findViewById(R.id.textView1);
        this.bt1=(Button)super.findViewById(R.id.button1);
        bt1.setOnClickListener(new ShowListener());
	}

    private class ShowListener implements OnClickListener{
        public void onClick(View V){
    		Recorder r = new Recorder();
    		Power p = new Power();
    		p.powerID = 6;
    		r.record(p);
        	tv1.setText(String.valueOf(p.powerID));
        } 
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
}
