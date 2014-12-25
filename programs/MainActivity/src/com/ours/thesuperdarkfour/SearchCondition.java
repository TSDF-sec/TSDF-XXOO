package com.ours.thesuperdarkfour;

/**
 * SearchCondition: 在数据库中搜索Power所用的条件
 * 可以同时搜索多个条件
 * 条件的定义：哪一维（whichDimension）+关系量词（>,=,<）+值
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
	
	//测试某个Power是否满足某个条件
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
