package com.ours.thesuperdarkfour;

/**
 * Plan ��ƵĽ���
 * �������г��������ݿ��к��е�powers����ʾ�ڽ����Ϲ��û�ѡȡ
 * �û����Ե��ÿ��power��Details��ť�������ǽ�����ϸ�����ã��������˵����ʱ��
 * ���û�ѡ������е�powerʱ�����ݿ��Ҫ����һ�飬ͬʱ���ر�ѡ�е�power��id
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import android.app.Activity;
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
		
		//���߼����л�ȡpower�����ݿ�
		Logic logic = Logic.getLogic();
		TableLayout view = (TableLayout)findViewById(R.id.powerchooseView);
		
		ArrayList<Power> pws = logic.powerdb.getAllPowers();
		//��ÿ��Power��ʾ��sView��
		
		for (Iterator<Power> iter = pws.iterator(); iter.hasNext();) {
			Power p = iter.next();
			addPowerDescription( view, p); 
		}
		
		Button btnOK = (Button) findViewById( R.id.buttonOK);
		btnOK.setOnClickListener(this);
	}

	public void addPowerDescription( TableLayout view, Power p) {
		//��power���ӵ�һ��������ʾ�Ľ�����
		//һ��power��һ��checkbox��һ��д��details�İ�ť��ɣ����details�ɽ����power����ϸ���ý���
		TableRow tablerow = new TableRow( this );
		
		CheckBox btn = new CheckBox(this);
		btn.setId(p.powerID);
		btn.setText(p.powerName);
		this.checkPowers.add(btn);
		tablerow.addView(btn);
		
		Button details = new Button( this );
		details.setText("Detail");
		details.setId(p.powerID);
		details.setOnClickListener(new onClickDetails( p.powerID ));
		tablerow.addView(details);
		
		view.addView(tablerow);
	}
	
	//���¡�details����ť�����Ӧ����
	public class onClickDetails implements OnClickListener {
		public int powerID;
		
		onClickDetails( int powerid ) {
			powerID = powerid;
		}
		
		public void onClick(View view) {
			int id = view.getId();
			//������power�����ý���
			Intent intent = new Intent(PlanDesignerActivity.this, PowerSettingActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			PlanDesignerActivity.this.startActivityForResult( intent, 100 );
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
		//Details�������
		Bundle bundle = data.getExtras();
		Power p = (Power) bundle.getSerializable("power");
		//�����power�����ݿ��н��и���
		Logic.getLogic().powerdb.setPower(p);
	}

	@Override
	public void onClick(View v) {
		// ���ok��ť����Ϊ
		// ����ѡ����powers
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

		// �洢���º�����ݿ�
		try {
			Logic logic = Logic.getLogic();
			logic.powerdb.savePower();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finish();
	}
}
