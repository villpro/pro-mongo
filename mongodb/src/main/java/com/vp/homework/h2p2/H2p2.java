package com.vp.homework.h2p2;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vp.homework.HomeWork;

public class H2p2 extends HomeWork {

	public static void main(String[] args) {
		DB sDB=getDB("students");
		DBCollection gColl=	sDB.getCollection("grades");
		DBObject sortCriteria= new BasicDBObject("student_id",-1).append("score", -1);
		DBObject typeIsHomeWork=new BasicDBObject("type","homework");
		DBCursor gItr=gColl.find(typeIsHomeWork).sort(sortCriteria); //.limit(12);
		
		if(gColl.count()==800)
			System.out.println("Total Record Before Remove="+gColl.count());
		
		ObjectId rID=null;
		Integer prevSID=null;
		Double score=null;
	/*	Integer lowScore=null;
		int sCount=0;*/
		List<ObjectId> rmlist= new ArrayList<ObjectId>();
		List <Double>scoreList= new ArrayList<Double>();
		try {
			while(gItr.hasNext()){
				DBObject dbObj=gItr.next();
				System.out.println(dbObj);
					Integer studentId=(Integer) dbObj.get("student_id");
					
					/**Add the entry in the list when student id changed,,
					 * that is when student id changed from 199 to 198 
					 * then add data of 199 to the list
					 * 
					 * update the previous student in prevSID
					 * 
					 */
					if(!(studentId.equals(prevSID)||prevSID==null)){
						//gItr.remove(); //not implemented now
						//sCount++;
						if (score!=null ) scoreList.add(score);
						if (rID!=null)  rmlist.add(rID);
					}
					
					score=(Double) dbObj.get("score");
					rID=(ObjectId)dbObj.get("_id");
					prevSID=studentId;
			}
			
			//add  last entry to the list
			rmlist.add(rID);
			scoreList.add(score);
		}
		finally{
			gItr.close();
			
		}
		
		
		for (Double object : scoreList) {
			System.out.println(object);
		}
		/*//######## Un comment to RUN  ##########
		  for (ObjectId id : rmlist) {
			gColl.remove(new BasicDBObject("_id",id));
		}*/
		
		if(gColl.count()==600)
			System.out.println("Total Record After Remove="+gColl.count());
	}

}
