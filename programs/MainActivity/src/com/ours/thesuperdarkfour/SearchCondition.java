package com.ours.thesuperdarkfour;

/**
 * SearchCondition: �����ݿ�������Power���õ�����
 * ����ͬʱ�����������
 * �����Ķ��壺��һά��whichDimension��+��ϵ���ʣ�>,=,<��+ֵ
 * @author GaoXiang
 *
 */
public class SearchCondition {
	SearchCondition( short whichDimension, Relationship relationship, float value){
		this.whichDimension = whichDimension;
		this.relationship = relationship;
		this.value = value;
		if(whichDimension<0 || whichDimension>=Dimension.NUM_OF_DIMENSIONS){
			System.out.println("The search condition is invalid.");
			_valid = false;
		}
	}
	
	//����ĳ��Power�Ƿ�����ĳ������
	public boolean satisfy( Power p ) {
		if (_valid==false) 
			return false;
		switch( this.relationship) {
		case EQUAL:
			return p.powerContributionToDimension[whichDimension] == value;
		case LARGER:
			return p.powerContributionToDimension[whichDimension] > value;
		case SMALLER:
			return p.powerContributionToDimension[whichDimension] < value;
		default:
			return false;
		}
	}
	public short whichDimension;
	public enum Relationship {LARGER, EQUAL, SMALLER};
	public Relationship relationship;
	public float value;
	public boolean _valid=true;
}
