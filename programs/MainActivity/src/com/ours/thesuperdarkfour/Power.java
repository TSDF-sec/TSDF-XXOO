package com.ours.thesuperdarkfour;

import java.io.Serializable;
import java.util.Date;

import android.text.Editable;

public class Power implements Serializable {
	//basic information
	Power() {
		for(int i=0; i<Dimension.NUM_OF_DIMENSIONS; i++)
			this.powerContributionToDimension[i] = 0.0f;
	}
	public short powerID=0; //在数据库中定义的id
	public String powerName="NoName"; //Power的名称
	public String powerDescription="NoDescription"; //Power的描述
	public float[] powerContributionToDimension 
		= new float[Dimension.NUM_OF_DIMENSIONS]; //该Power在单位时间内对各个维度的贡献
	
	//type
	//Power的类型分为几种：
	//(1)完成型：每天只需选择是否完成。
	//(2)持续型：每天需要计算持续时间的活动
	public enum PowerType{COMPLETE, CONTINUE};
	public PowerType powertype; 
	
	//time
	//For 持续型 only
	public Date targetTime = new Date();
	public Date actualTime = new Date();
	
	//record
	public Recorder powerRecorder; //完成后的Power交由Recorder类处理
	
	public boolean complete = false; //是否完成
}
