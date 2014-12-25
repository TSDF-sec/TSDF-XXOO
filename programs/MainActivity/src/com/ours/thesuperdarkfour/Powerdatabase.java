package com.ours.thesuperdarkfour;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Powerdatabase: Power数据库
 * 通过该数据库与磁盘上存储着的Power进行交互
 * 格式：Powerid+换行
 * 名称+换行
 * 对五维的贡献 +换行
 * 说明 +换行
 * @author GaoXiang
 *	注意！！！
 * Power名称里面不要带空格！因为空格是分隔符！
 */
public class Powerdatabase {

	public static final String DATABASE_NAME="power.db";
	private ArrayList<Power> powers;
	short _currID = 0;
	//构造函数，不需多解释了吧。。。
	//还是解释下，它会寻找当前目录下的power.db并从中读取所有power信息，如果没有该文件，就是空的。
	public Powerdatabase() throws IOException {
		powers = new ArrayList();
		FileReader fr;
		try {
			fr = new FileReader(DATABASE_NAME);
			//如果文件存在，则从文件中读取所有的Power
			loadPower(fr);
		} catch (FileNotFoundException e) {
			System.out.println("The power database does not exist.");
		}
		System.out.println("Init Powerdatabase ok.");
	}
	
	
	//从FileReader中读取所有的Power
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
		//将所有的Power都存进数据文件中
		for (Iterator iter = powers.iterator(); iter.hasNext();) {
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
	
	//向数据库中增加一个Power
	public int addPower(Power p) {
		p.powerID = _currID;
		_currID++;
		powers.add(p);
		return 0;
	}
	
	//获取所有Power
	public ArrayList<Power> getAllPowers() {
		return powers;
	}

	//清空已存在的Power
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
	
	//测试用的main
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
