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
	
	//�������û����洢��TXT����Ҫָ��ID�����֡����䡢�Ա�����
	public void createNewUser(Activity activity, int newID, String newName, int newAge, Gender newGender) {
		user.userID = newID;
		user.userName = newName;
		user.userAge = newAge;
		user.userGender = newGender;
		user.saveUserInfoToFile(activity);

	}

	//����TXT�д洢���û���Ϣ
	public boolean loadUserInfo(Activity activity) {
		return user.loadUserInfoFromFile(activity);
	}
	
	//�����û���Ϣ
	public User getUser() {
		return this.user;
	}
	
	//Ψһ��
	private static Logic _logic;
	public static Logic getLogic() {
		return _logic;
	}
	public static void setInitLogic( Logic logic ) 
	{
		_logic = logic;
	}

}
