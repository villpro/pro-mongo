package com.vp.homework.h2p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vp.homework.HomeWork;

/**
 * Answer _id =13
 * @author prohal
 *
 */
public class H3p1 extends HomeWork {

	public static void main(String[] args) {
		DB sDB=getDB("school");
		DBCollection gColl=	sDB.getCollection("students");
		DBObject sortCriteria= new BasicDBObject("_id",-1);//.append("scores.score", -1);
		DBObject typeIsHomeWork=new BasicDBObject("scores.type","homework");
		DBCursor gItr=gColl.find(typeIsHomeWork).sort(sortCriteria);//.limit(2);
		
		System.out.println("Total Record Before Remove="+gColl.count());
		List<Integer> rStdList= new ArrayList<Integer>();
		List <Double> rScoreList= new ArrayList<Double>();
			
		try {
			while(gItr.hasNext()){
				DBObject dbObj=gItr.next();
				System.out.println(dbObj);
					BasicDBList scores=(BasicDBList) dbObj.get("scores");
					Integer stID=(Integer) dbObj.get("_id");
					Iterator itr= scores.iterator();
					Double minScore=100.0;
					
					while (itr.hasNext()) {
									BasicDBObject o=(BasicDBObject) itr.next();
									if("homework".equals(o.get("type")))minScore=minScore >(Double) o.get("score")? (Double) o.get("score"): minScore;
					}
				if (minScore != 100.0) {
					rScoreList.add(minScore);
					rStdList.add(stID);
				}
			
					
			}
		}
		finally{
			gItr.close();
			
		}
		
		//######## Un comment to RUN  ##########
		/**
		 * Remove one by one from db
		 * 
		 */
		
		/*for (int i =0;i<rScoreList.size();i++) {
		    BasicDBObject match = new BasicDBObject("_id", rStdList.get(i)); // to match your document
			BasicDBObject update = new BasicDBObject("scores", new BasicDBObject("score", rScoreList.get(i)));
			gColl.update(match, new BasicDBObject("$pull", update));
			}*/
		}
		
	}


