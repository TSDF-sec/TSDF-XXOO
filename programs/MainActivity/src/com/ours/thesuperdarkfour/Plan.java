package com.ours.thesuperdarkfour;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
/*
 * Plan ���ƻ����ĸ���
 * ÿ���û���ͬһʱ��ֻӵ��һ��Plan���ں����ɸ�Power����
 * �û��ɶ����Լ���Plan�������ݿ���ѡ��һЩPower��
 * ÿ�죬�û��ɲ�ѡ��������е�ĳЩPower��
 */
import java.util.Iterator;


public class Plan {
	public int planID;
	public ArrayList<Power> powers = new ArrayList<Power> (); //��Plan�к��е�Power
	
	//�˺����ɶ���Plan�Ľ������
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
	
	//���µ���״̬
	//������Power״̬����δ���
	public int update() {
		DateFormat d = DateFormat.getDateTimeInstance();
		d.format(currentDate);
		for (Iterator iter = powers.iterator(); iter.hasNext(); ) {
			Power p = (Power)iter.next();
			p.complete = false;		
		}
		return 0;
	}
	
	public Date startDate; //��ʼ����
	public Date currentDate; //��ǰ����
	public Date endDate; //��ֹ����
}
