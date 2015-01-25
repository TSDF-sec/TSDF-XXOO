package com.ours.thesuperdarkfour;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;

import com.ours.thesuperdarkfour.User.Gender;

public class Logic {
	
	private User user = new User();
	public Powerdatabase powerdb;
	
	Logic() throws IOException {
		powerdb = new Powerdatabase();
	}
	
	//建立新用户并存储至TXT，需要指定ID、名字、年龄、性别、密码
	public void createNewUser(Activity activity, int newID, String newName, int newAge, Gender newGender) {
		user.userID = newID;
		user.userName = newName;
		user.userAge = newAge;
		user.userGender = newGender;
		user.saveUserInfoToFile(activity);

	}

	//加载TXT中存储的用户信息
	public boolean loadUserInfo(Activity activity) {
		return user.loadUserInfoFromFile(activity);
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

}
