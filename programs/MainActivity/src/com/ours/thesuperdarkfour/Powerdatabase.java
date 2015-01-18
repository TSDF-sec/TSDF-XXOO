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
 * Powerdatabase: Power数据库
 * 通过该数据库与磁盘上存储着的Power进行交互
 * 格式：Powerid+换行
 * 名称+换行
 * 对五维的贡献 +换行
 * 说明 +换行
 * @author GaoXiang
 */
public class Powerdatabase {

	private final String DATABASE_NAME="./power.db";
	private ArrayList<Power> powers;
	short _currID = 0;
	
	//五维属性：事业、幸福、健康、社交、兴趣
	//默认的Power：比较长，不想看的把它折叠掉吧
	private final Power[] defaultPowers = {
			new Power( "早起", "早起对健康可是有益的呢",
					0f,0f,1.0f,0f,0f, new Date(0,0,0,7,0)),
			new Power( "睡懒觉", "睡懒觉对健康可是有益的呢，但是睡多了可没时间干活了哦",
					-1.0f,0.01f,.5f,0f,0f, new Date(0,0,0,10,0)),
			new Power( "学习java", "学习java是所有程序员必经之路！",
					1.0f,0.2f,0.0f,0f,0.2f, new Date(0,0,0,2,0)),
			new Power( "学习C++", "虽然C++很难但我仍要好好学习它!",
					1.2f,0.2f,0.0f,0f,0.2f, new Date(0,0,0,2,0)),
			new Power( "学习python", "Python是世界上最厉害的语言！",
					1.1f,0.5f,0.0f,0f,0.3f, new Date(0,0,0,2,0)),
			new Power( "学习Qt", "Qt是最好的GUI!",
					1.01f,0.5f,0.0f,0f,0.3f, new Date(0,0,0,2,0)),
			new Power( "按时吃三餐", "吃饭是革命的本钱！",
					0f,0.2f,1.0f,0f,0.0f, new Date(0,0,0,1,0)),
			new Power( "早睡不熬夜", "习大大这么说的",
					0f,0.5f,1.5f,0f,0.0f, new Date(0,0,0,23,0)),
			new Power( "和朋友一起玩", "朋友！才是人生最珍贵的宝物啊！",
					0f,1.5f,0.2f,1.0f,0.0f, new Date(0,0,0,1,0)),
			new Power( "培养一小时兴趣爱好", "给自己的心一个小时的暖意",
					0f,0.5f,0.1f,0f,1.0f, new Date(0,0,0,1,0)),
			new Power( "练习三黄对点的剑圣", "果然三黄对点才是真正的剑圣",
					0f,0.1f,-0.2f,0f,0.2f, new Date(0,0,0,1,0)),
			new Power( "做老板的项目", "项目不得不做啊",
					1.2f,-0.5f,-0.5f,-0.1f,0.f, new Date(0,0,0,2,0)),
			new Power( "健身", "去健身房理解男人的浪漫吧！",
					0.f,.2f,1.5f,0.1f,0.2f, new Date(0,0,0,0,30)),
	};
	
	//构造函数，不需多解释了吧。。。
	//还是解释下，它会寻找当前目录下的power.db并从中读取所有power信息
	//如果没有该文件，就是空的。
	
	public Powerdatabase() throws IOException {
		powers = new ArrayList();
		FileReader fr;
		try {
			fr = new FileReader (DATABASE_NAME);
			//如果文件存在，则从文件中读取所有的Power
			loadPower(fr);
		} catch (FileNotFoundException e) {
			System.out.println("The power database does not exist.");
		}
		System.out.println("Init Powerdatabase ok.");
		
		//增加一系列默认的Power
		for (int i =0; i<defaultPowers.length; i++) {
			this.addPower(defaultPowers[i]);
		}
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
	
	public Power getPower( short id ) {
		for (Iterator iter = powers.iterator(); iter.hasNext(); ) {
			Power p = (Power) iter.next();
			if (p.powerID == id) {
				return p;
			}
		}
		return null;
	}
	
	//向数据库中更新一个power，如已存在，覆盖之前的，如不存在则加到列表中
	public boolean setPower( Power power ) {
		int index = 0;
		for (Iterator iter = powers.iterator(); iter.hasNext(); ) {
			Power p = (Power) iter.next();
			if (p.powerID == power.powerID) {
				powers.set( index, power);
				return true;
			}
			index++;
		}
		addPower( power );
		return false;
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
