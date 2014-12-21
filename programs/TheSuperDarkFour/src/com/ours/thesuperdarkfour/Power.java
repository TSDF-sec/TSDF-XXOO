package com.ours.thesuperdarkfour;

public class Power {
	//basic information
	public short powerID;
	public String powerName;
	public Dimension powerContributionToDimension;
	
	//type
	public boolean tickOnly; 
	
	//time
	public short targetTime;
	public short actualTime;
	
	//record
	public Recorder powerRecorder;
}
