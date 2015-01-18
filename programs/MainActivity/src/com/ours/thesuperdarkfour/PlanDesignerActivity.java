package com.ours.thesuperdarkfour;

/**
 * Plan 设计的界面
 * 这个类会列出所有数据库中含有的powers，显示在界面上供用户选取
 * 用户可以点击每个power的Details按钮，对它们进行详细的设置，例如更改说明、时间
 * 当用户选择好所有的power时，数据库就要更新一遍，同时返回被选中的power的id
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlanDesignerActivity extends Activity implements OnClickListener {

	protected ArrayList<CheckBox> checkPowers = new ArrayList<CheckBox> ();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_designer);
		
		//从逻辑类中获取power的数据库
		Logic logic = Logic.getLogic();
		TableLayout view = (TableLayout)findViewById(R.id.powerchooseView);
		
		ArrayList<Power> pws = logic.powerdb.getAllPowers();
		//把每个Power显示在sView中
		
		for (Iterator<Power> iter = pws.iterator(); iter.hasNext();) {
			Power p = iter.next();
			addPowerDescription( view, p); 
		}
		
		Button btnOK = (Button) findViewById( R.id.buttonOK);
		btnOK.setOnClickListener(this);
	}

	public void addPowerDescription( TableLayout view, Power p) {
		//将power增加到一个竖着显示的界面里
		//一个power由一个checkbox和一个写着details的按钮组成，点击details可进入该power的详细设置界面
		TableRow tablerow = new TableRow( this );
		
		CheckBox btn = new CheckBox(this);
		btn.setId(p.powerID);
		btn.setText(p.powerName);
		this.checkPowers.add(btn);
		tablerow.addView(btn);
		
		Button details = new Button( this );
		details.setText("Detail");
		details.setId(p.powerID);
		details.setOnClickListener(new onClickDetails( p.powerID, this));
		tablerow.addView(details);
		
		view.addView(tablerow);
	}
	
	//按下“details”按钮后的响应函数
	public class onClickDetails implements OnClickListener {
		public int powerID;
		Activity parent;
		onClickDetails( int powerid, Activity parent ) {
			powerID = powerid;
			this.parent = parent;
		}
		
		public void onClick(View view) {
			int id = view.getId();
			//弹出该power的设置界面
			Intent intent = new Intent( parent, PowerSettingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			parent.startActivityForResult( intent, 100 );
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.plan_designer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onclickClose( View view ) {
		this.onDestroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Details结果返回
		Bundle bundle = data.getExtras();
		Power p = (Power) bundle.getSerializable("power");
		//将这个power在数据库中进行更新
		Logic.getLogic().powerdb.setPower(p);
	}

	@Override
	public void onClick(View v) {
		// 点击ok按钮的行为
		// 返回选定的powers
		boolean[] checked = new boolean[ Logic.getLogic().powerdb.getAllPowers().size() ];
		int index=0;
		for (Iterator<CheckBox> iter = this.checkPowers.iterator(); iter.hasNext(); ) {
			CheckBox c = (CheckBox) iter.next();
			if ( c.isChecked() )
				checked[index] = true;
			else
				checked[index] = false;
			index++;
		}
		
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		
		bundle.putBooleanArray("checked", checked);
		intent.putExtras(bundle);
		setResult(0, intent);

		// 存储更新后的数据库
		try {
			Logic logic = Logic.getLogic();
			logic.powerdb.savePower();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finish();
	}
	
	public void onClickAddNewPower( View v) {
		//点击“新增Power”键
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("很遗憾");  
		builder.setMessage("您看，我们现在还不允许用户自己定义Power哦"); 
		builder.setPositiveButton("确定",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        ;
                    }  
                });
		builder.show();
	}
}
