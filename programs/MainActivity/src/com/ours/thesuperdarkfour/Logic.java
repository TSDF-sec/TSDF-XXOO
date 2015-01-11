package com.ours.thesuperdarkfour;

import java.io.IOException;
import java.util.ArrayList;

import com.ours.thesuperdarkfour.User.Gender;

public class Logic {
	
	private User user = new User();
	public Powerdatabase powerdb;
	
	Logic() throws IOException {
		powerdb = new Powerdatabase();
	}
	
	//建立新用户并存储至TXT，需要指定ID、名字、年龄、性别、密码
	public void createNewUser(int newID, String newName, int newAge, Gender newGender, String newPasscode) {
		user.userID = newID;
		user.userName = newName;
		user.userAge = newAge;
		user.userGender = newGender;
		user.userPasscode = newPasscode;
		try {
			user.saveUserInfoToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//加载TXT中存储的用户信息
	public void loadUserInfo() {
		try {
			user.loadUserInfoFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//返回用户信息
	public User getUser() {
		return this.user;
	}
	
	//唯一性
	private static Logic _logic;
	public static Logic getLogic() {
		return _logic;
	}
	public static void setInitLogic( Logic logic ) 
	{
		_logic = logic;
	}
	
	//也是测试用的main函数
	public static void main(String[] args) throws IOException {
		Logic logic = new Logic();
		Logic.setInitLogic(logic);
		
//		Logic.getLogic().createNewUser(222, "GX", 88, Gender.MALE, "wangtongxue");
		Logic.getLogic().loadUserInfo();
		
		System.out.println("Current User is " + Logic.getLogic().user.userName);
		System.out.println("His/Her ID is " + Logic.getLogic().user.userID);
		System.out.println("His/Her Age is " + Logic.getLogic().user.userAge);
		System.out.println("His/Her PassCode is " + Logic.getLogic().user.userPasscode);
		
		ArrayList<Power> powers = Logic.getLogic().powerdb.getAllPowers();
		Plan plan = new Plan();
		plan.addPower(powers);
		Logic.getLogic().user.addPlan(plan);
		
	}

}
