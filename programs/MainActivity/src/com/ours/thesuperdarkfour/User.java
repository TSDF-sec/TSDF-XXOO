package com.ours.thesuperdarkfour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore.Audio.PlaylistsColumns;
import android.widget.TextView;

public class User {
	public static final String fileName = "User.txt";
	
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
	public void saveUserInfoToFile(Activity activity) {
		try {
            FileOutputStream outputStream = activity.openFileOutput(fileName, Activity.MODE_PRIVATE);
            outputStream.write(Integer.toString(this.userID).getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(this.userName.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(Integer.toString(this.userAge).getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(String.valueOf(this.userGender).getBytes());
            outputStream.write("\n".getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean loadUserInfoFromFile(Activity activity) {
		try {
            FileInputStream inputStream = activity.openFileInput(fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            String content = new String(arrayOutputStream.toByteArray());
            String [] strings = content.split("\n");
            System.out.println(strings.length);
            if (strings.length == 5) {
	            this.userID = Integer.valueOf(strings[0]);
	            this.userName = strings[1];
	            this.userAge = Integer.valueOf(strings[2]);
	            this.userGender = Gender.valueOf(strings[3]);  
	            return true;
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public void addPlan(Plan plan) {
		this.userPlan = plan;
		
		String planToFile = "";
		for (int i=0;i<plan.powers.size();i++) {
			String nowID = Short.toString(plan.powers.get(i).powerID);
			planToFile = planToFile + nowID + ' ';
		}
		
		
		String fileName = "/User.txt";
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
