package test;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import Server.DBStoreImpl;

import com.briup.util.BIDR;

import Client.GatherImpl;

public class GatherTest {
	public static void main(String[] args) {
		
		GatherImpl gi = new GatherImpl();
//		Iterator<BIDR> iter = gi.gather().iterator();
//		while(iter.hasNext()){
//			System.out.println(iter.next());
//		}
		Collection<BIDR> bidrs = gi.gather();
		
		DBStoreImpl db = new DBStoreImpl();
		try {
			db.saveToDB(bidrs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

