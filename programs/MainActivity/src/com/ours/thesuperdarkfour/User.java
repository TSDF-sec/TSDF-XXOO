package com.ours.thesuperdarkfour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class User {
	//basic information
	public int userID;
	public String userName;
	public int userAge;
	public enum Gender{MALE,FEMALE,SHEMALE};
	public Gender userGender;
	public String userPasscode;
	
	//5 dimensions
	public Dimension userDimension;
	
	//plans
	public Plan [] userPlan;
	
	//friends
	public User [] friendlist;
	
	//accomplishments
	
	//constructors
	public User(){};
	
	public User(int newID, String newName, int newAge, Gender newGender, String newPasscode) {
		this.userID = newID;
		this.userName = newName;
		this.userAge = newAge;
		this.userGender = newGender;
		this.userPasscode = newPasscode;
	}
	
	//functions
	public void saveUserInfoToFile() {
		BufferedWriter bufferedWriter = null;
		try {
			File file = new File("User.txt");
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			bufferedWriter.append(String.valueOf(this.userID));
			bufferedWriter.newLine();
			bufferedWriter.append(this.userName);
			bufferedWriter.newLine();
			bufferedWriter.append(String.valueOf(this.userAge));
			bufferedWriter.newLine();
			bufferedWriter.append(this.userGender.name());
			bufferedWriter.newLine();
			bufferedWriter.append(this.userPasscode);
			bufferedWriter.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void loadUserInfoFromFile() {
		String filePath = "User.txt";
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			this.userID = Integer.valueOf(bufferedReader.readLine());
			this.userName = bufferedReader.readLine();
			this.userAge = Integer.valueOf(bufferedReader.readLine());
			this.userGender = Gender.valueOf(bufferedReader.readLine());
			this.userPasscode = bufferedReader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
