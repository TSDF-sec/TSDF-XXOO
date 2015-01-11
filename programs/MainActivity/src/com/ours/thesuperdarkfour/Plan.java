package com.ours.thesuperdarkfour;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
/*
 * Plan “计划”的概念
 * 每个用户在同一时间只拥有一个Plan，内含若干个Power对象
 * 用户可定制自己的Plan（从数据库中选择一些Power）
 * 每天，用户可并选择完成其中的某些Power。
 */
import java.util.Iterator;


public class Plan {
	public int planID;
	public ArrayList<Power> powers = new ArrayList<Power> (); //该Plan中含有的Power
	
	//此函数由定制Plan的界面调用
	public void addPower( ArrayList<Power> ap ) {
		for (int i=0; i<ap.size(); i++)	
			powers.add(ap.get(i));
	}
	
	public ArrayList<Power> getCompletedPowerToday() {
		ArrayList<Power> results = new ArrayList<Power> ();
		for (Iterator iter = powers.iterator(); iter.hasNext(); ) {
			Power p = (Power)iter.next();
			if (p.complete)
				results.add(p);
		}
		return results;
	}
	
	//更新当天状态
	//将所有Power状态置于未完成
	public int update() {
		DateFormat d = DateFormat.getDateTimeInstance();
		d.format(currentDate);
		for (Iterator iter = powers.iterator(); iter.hasNext(); ) {
			Power p = (Power)iter.next();
			p.complete = false;		
		}
		return 0;
	}
	
	public Date startDate; //开始日期
	public Date currentDate; //当前日期
	public Date endDate; //终止日期
}
