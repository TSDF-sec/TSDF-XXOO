package com.ours.thesuperdarkfour;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import android.app.Activity;
import android.content.res.AssetManager;

/**
 * Powerdatabase: Power���ݿ�
 * ͨ�������ݿ�������ϴ洢�ŵ�Power���н���
 * ��ʽ��Powerid+����
 * ����+����
 * ����ά�Ĺ��� +����
 * ˵�� +����
 * @author GaoXiang
 *	ע�⣡����
 * Power�������治Ҫ���ո���Ϊ�ո��Ƿָ�����
 */
public class Powerdatabase {

	private final String DATABASE_NAME="./power.db";
	private ArrayList<Power> powers;
	short _currID = 0;
	//���캯�������������˰ɡ�����
	//���ǽ����£�����Ѱ�ҵ�ǰĿ¼�µ�power.db�����ж�ȡ����power��Ϣ�����û�и��ļ������ǿյġ�
	public Powerdatabase() throws IOException {
		powers = new ArrayList();
		FileReader fr;
		try {
			fr = new FileReader (DATABASE_NAME);
			//����ļ����ڣ�����ļ��ж�ȡ���е�Power
			loadPower(fr);
		} catch (FileNotFoundException e) {
			System.out.println("The power database does not exist.");
		}
		System.out.println("Init Powerdatabase ok.");
		
		//������
		Power p = new Power();
		p.powerName = "Get up early";
		p.powerDescription = "Get up before 6:00!";
		p.powerContributionToDimension[0]=1.0f;
		addPower(p);
		
//		Power p2 = new Power();
//		p2.powerName = "Practice Java";
//		p2.powerDescription = "Practice Java for 8 hours!";
//		p2.powerContributionToDimension[0]=1.0f;
//		addPower(p2);
	}
	
	
	//��FileReader�ж�ȡ���е�Power
	public int loadPower( FileReader fr ) throws IOException {
		BufferedReader br = new BufferedReader( fr );
		while( true ) {
			String data = br.readLine();
			if (data==null) break;
			Power p = new Power();
			p.powerID = (short) Integer.parseInt(data);
			data = br.readLine();
			p.powerName = data;
			data = br.readLine();
			String d[] = data.split(" ");
			for(int i=0; i<Dimension.NUM_OF_DIMENSIONS; i++) {
				p.powerContributionToDimension[i] = Float.parseFloat(d[i]);
			}
			data = br.readLine();
			p.powerDescription = data;
			powers.add(p);
		}
		_currID = (short) powers.size();
		System.out.printf("Load %d powers", _currID-1);
		return 0;
	}
	
	public int savePower() throws IOException {
		FileWriter fw = new FileWriter( DATABASE_NAME );
		//�����е�Power����������ļ���
		for (Iterator<Power> iter = powers.iterator(); iter.hasNext();) {
			String buffer = new String();
			Power p = (Power) iter.next();
			fw.write(p.powerID+"\n");
			fw.write(p.powerName+"\n");
			buffer="";
			for (int i=0; i<Dimension.NUM_OF_DIMENSIONS; i++)
				buffer = buffer+ (p.powerContributionToDimension[i]+" ");
			buffer+="\n";
			fw.write(buffer);
			fw.write(p.powerDescription+"\n");
		}
		System.out.println("save ok.");
		fw.close();
		return 0;
	}
	
	//�����ݿ�������һ��Power
	public int addPower(Power p) {
		p.powerID = _currID;
		_currID++;
		powers.add(p);
		return 0;
	}
	
	//��ȡ����Power
	public ArrayList<Power> getAllPowers() {
		return powers;
	}

	//����Ѵ��ڵ�Power
	public int clearAllPower() {
		powers.clear();
		_currID = 0;
		return 0;
	}

	public ArrayList<Power> search( ArrayList<SearchCondition> conditions) {
		ArrayList<Power> results = new ArrayList<Power> ();
		for (Iterator iter = powers.iterator(); iter.hasNext();) {
			Power p = (Power)iter.next();
			boolean ok = true;
			for (Iterator iter2 = conditions.iterator(); iter2.hasNext();) {
				SearchCondition c = (SearchCondition) iter2.next();
				if (!c.satisfy(p)) {
					ok = false;
					break;
				}
			}
			if (ok == true) {
				results.add(p);
			}
		}
		return results;
	}
	
	//�����õ�main
	public static void main(String args[]) {
		try {
			Powerdatabase pdb = new Powerdatabase();
			Power p = new Power();
//			p.powerName = "Get_up_early";
//			p.powerDescription = "Get up before 6:00!";
//			p.powerContributionToDimension[0]=1.0f;
//			pdb.addPower(p);
//			pdb.savePower();
			ArrayList<Power> powers = pdb.getAllPowers();
			System.out.println("Here are powers in the database:\n");
			for (Iterator iter=powers.iterator(); iter.hasNext();) {
				Power temp = (Power)iter.next();
				System.out.print(temp.powerID+"\n");
				System.out.print(temp.powerName+"\n");
				System.out.print(temp.powerDescription+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}