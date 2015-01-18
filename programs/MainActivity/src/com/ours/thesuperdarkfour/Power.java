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
	
	//���캯��
	Power( String name, String description, float c1, float c2, 
			float c3, float c4, float c5, Date t) {
		powerName = name;
		this.powerDescription = description;
		this.powerContributionToDimension[0] = c1;
		this.powerContributionToDimension[1] = c2;
		this.powerContributionToDimension[2] = c3;
		this.powerContributionToDimension[3] = c4;
		this.powerContributionToDimension[4] = c5;
		targetTime = t;
	}
	
	public short powerID=0; //�����ݿ��ж����id
	public String powerName="NoName"; //Power������
	public String powerDescription="NoDescription"; //Power������
	public float[] powerContributionToDimension 
		= new float[Dimension.NUM_OF_DIMENSIONS]; //��Power��һСʱ�ڶԸ���ά�ȵĹ���
	
	//type
	//Power�����ͷ�Ϊ���֣�
	//(1)����ͣ�ÿ��ֻ��ѡ���Ƿ���ɡ�
	//(2)�����ͣ�ÿ����Ҫ�������ʱ��Ļ
	public enum PowerType{COMPLETE, CONTINUE};
	public PowerType powertype; 
	
	//time
	//For ������ only
	public Date targetTime = new Date();
	public Date actualTime = new Date();
	
	//record
	public Recorder powerRecorder; //��ɺ��Power����Recorder�ദ��
	
	public boolean complete = false; //�Ƿ����
}
