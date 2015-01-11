package com.ours.thesuperdarkfour;

import java.io.IOException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
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
        
        //Logic的初始化函数
        Logic logic;
		try {
			logic = new Logic();
	        Logic.setInitLogic(logic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public class ShowListener implements OnClickListener{
        public void onClick(View V){
    		Recorder r = new Recorder();
    		Power p = new Power();
    		r.record(p);
        	tv1.setText(String.valueOf(p.powerID));
        	
        	//测试代码：打开一个plan designer的界面
        	Intent intent = new Intent(MainActivity.this, PlanDesignerActivity.class);
//        	intent.setComponent(new ComponentName(
//        			"com.ours.thesuperdarkfour",
//        			"com.ours.thesuperdarkfour.PlanDesignerActivity"));
        	MainActivity.this.startActivity( intent );
        	
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
