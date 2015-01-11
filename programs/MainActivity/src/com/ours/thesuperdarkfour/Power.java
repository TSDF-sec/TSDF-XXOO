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
	public short powerID=0; //�����ݿ��ж����id
	public String powerName="NoName"; //Power������
	public String powerDescription="NoDescription"; //Power������
	public float[] powerContributionToDimension 
		= new float[Dimension.NUM_OF_DIMENSIONS]; //��Power�ڵ�λʱ���ڶԸ���ά�ȵĹ���
	
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
