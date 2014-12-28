package com.ours.thesuperdarkfour;

import java.io.IOException;

import com.ours.thesuperdarkfour.User.Gender;

public class Logic {
	
	private User user = new User();
	public Plan plan = new Plan();
	public Powerdatabase powerdb;
	
	Logic() throws IOException {
		powerdb = new Powerdatabase();
	}
	
	//�������û����洢��TXT����Ҫָ��ID�����֡����䡢�Ա�����
	public void createNewUser(int newID, String newName, int newAge, Gender newGender, String newPasscode) {
		user.userID = newID;
		user.userName = newName;
		user.userAge = newAge;
		user.userGender = newGender;
		user.userPasscode = newPasscode;
		user.saveUserInfoToFile();
	}

	//����TXT�д洢���û���Ϣ
	public void loadUserInfo() {
		user.loadUserInfoFromFile();
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
	
	//Ҳ�ǲ����õ�main����
	public static void main(String[] args) throws IOException {
		Logic logic = new Logic();
		Logic.setInitLogic(logic);
		
//		Logic.getLogic().createNewUser(222, "GX", 88, Gender.MALE, "wangtongxue");
		Logic.getLogic().loadUserInfo();
		
		System.out.println("Current User is " + Logic.getLogic().user.userName);
		System.out.println("His/Her ID is " + Logic.getLogic().user.userID);
		System.out.println("His/Her Age is " + Logic.getLogic().user.userAge);
		System.out.println("His/Her PassCode is " + Logic.getLogic().user.userPasscode);
		
		
	}

}
