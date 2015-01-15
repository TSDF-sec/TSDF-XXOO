package com.ours.thesuperdarkfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

public class PowerSettingActivity extends Activity implements OnClickListener {

	public Power power;
	
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_power_setting);
		
		Logic logic = Logic.getLogic();
		Intent intent = this.getIntent();
		power = logic.powerdb.getPower( (short) intent.getIntExtra("id", 0) );
		
		//将控件里的说明部分置成该power的状态
		if ( power != null ) {
			EditText editPowerName = (EditText)findViewById(R.id.powerName);
			editPowerName.setText( power.powerName );
			
			EditText editPowerDesp = (EditText)findViewById(R.id.powerDescription);
			editPowerDesp.setText( power.powerDescription );
			
			TimePicker timepicker = (TimePicker)findViewById(R.id.time);
			timepicker.setIs24HourView(true);
			timepicker.setCurrentHour(power.targetTime.getHours());
			timepicker.setCurrentMinute( power.targetTime.getMinutes());
		
			CheckBox checkContinue = (CheckBox)findViewById( R.id.checkContinue );
			checkContinue.setChecked(power.powertype==Power.PowerType.CONTINUE);
			
			Button btnOK = (Button)findViewById( R.id.settingOK);
			btnOK.setOnClickListener( this );
			
			EditText contributionText = (EditText)findViewById(R.id.contribution);
			String con = "";
			for (int i=0; i<Dimension.NUM_OF_DIMENSIONS; i++) {
				con+=Dimension.discription[i]+":"
							+power.powerContributionToDimension[i]+"\n";
			}
			contributionText.setText( con );
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.power_setting, menu);
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

	@Override
	public void onClick(View v) {
		//按下“OK”键时的回调函数
		//更新power的参数，关闭当前activity
		EditText editPowerName = (EditText)findViewById(R.id.powerName);
		power.powerName = editPowerName.getText().toString();
		
		EditText editPowerDesp = (EditText)findViewById(R.id.powerDescription);
		power.powerDescription = editPowerDesp.getText().toString();
		
		TimePicker timepicker = (TimePicker)findViewById(R.id.time);
		power.targetTime.setHours(timepicker.getCurrentHour());
		power.targetTime.setMinutes(timepicker.getCurrentMinute());
	
		CheckBox checkContinue = (CheckBox)findViewById( R.id.checkContinue );
		if (checkContinue.isChecked()) {
			power.powertype = Power.PowerType.CONTINUE;
		} else {
			power.powertype = Power.PowerType.COMPLETE;
		}
		
		Intent data = new Intent();
		Bundle bundle = new Bundle();
		bundle.putSerializable("power", power);
		data.putExtras(bundle);
		setResult( 0, data );
		this.finish();
	}
	
	//重写返回键的回调函数：注意一定要加这个，否则会出错
	@Override
	public void onBackPressed() {
		onClick(null);
	}
}
