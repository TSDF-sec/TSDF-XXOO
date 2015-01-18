package com.ours.thesuperdarkfour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.provider.MediaStore.Audio.PlaylistsColumns;

public class User {
	//basic information
	public int userID;
	public String userName;
	public int userAge;
	public enum Gender{MALE,FEMALE,SHEMALE};
	public Gender userGender;
	
	//5 dimensions
	public Dimension userDimension;
	
	//plans
	public Plan userPlan;
	
	//friends
	public User friendlist;
	
	//accomplishments
	
	//constructors
	public User(){};
	
	public User(int newID, String newName, int newAge, Gender newGender) {
		this.userID = newID;
		this.userName = newName;
		this.userAge = newAge;
		this.userGender = newGender;
	}
	
	//functions
	public void saveUserInfoToFile() throws IOException {
			
		File file = new File("./User.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
		bufferedWriter.append(String.valueOf(this.userID));
		bufferedWriter.newLine();
		bufferedWriter.append(this.userName);
		bufferedWriter.newLine();
		bufferedWriter.append(String.valueOf(this.userAge));
		bufferedWriter.newLine();
		bufferedWriter.append(this.userGender.name());
		bufferedWriter.newLine();
		
		bufferedWriter.close();
	}
	
	public void loadUserInfoFromFile() throws IOException {
		String filePath = "./User.txt";
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
		this.userID = Integer.valueOf(bufferedReader.readLine());
		this.userName = bufferedReader.readLine();
		this.userAge = Integer.valueOf(bufferedReader.readLine());
		this.userGender = Gender.valueOf(bufferedReader.readLine());

		bufferedReader.close();

	}
	
	public void addPlan(Plan plan) {
		this.userPlan = plan;
		
		String planToFile = "";
		for (int i=0;i<plan.powers.size();i++) {
			String nowID = Short.toString(plan.powers.get(i).powerID);
			planToFile = planToFile + nowID + ' ';
		}
		
		
		String fileName = "./User.txt";
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			bufferedWriter.append(planToFile);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
