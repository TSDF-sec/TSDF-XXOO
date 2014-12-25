package com.ours.thesuperdarkfour;

public class User {
	//basic information
	private short userID;
	private String userName;
	private boolean userGender;
	
	public short getID() {return userID;}
	public String getName() {return userName;}
	public boolean getGender() {return userGender;}
	
	//5 dimensions
	public Dimension userDimension;
	
	//plans
	public Plan [] userPlan;
	
	//friends
	public User [] friendlist;
	
	//accomplishments
}
