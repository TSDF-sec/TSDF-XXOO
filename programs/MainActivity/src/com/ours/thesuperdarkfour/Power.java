package com.ours.thesuperdarkfour;

import java.util.Date;

public class Power {
	//basic information
	Power() {
		for(int i=0; i<Dimension.NUM_OF_DIMENSIONS; i++)
			this.powerContributionToDimension[i] = 0.0f;
	}
	public short powerID=0;
	public String powerName="NoName";
	public String powerDescription="NoDescription";
	public float[] powerContributionToDimension = new float[Dimension.NUM_OF_DIMENSIONS];
	
	//type
	public boolean tickOnly=false; 
	
	//time
	public Date targetTime;
	public Date actualTime;
	
	//record
	public Recorder powerRecorder;
}
