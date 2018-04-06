package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConn {
	MongoClient mongoClient;
	MongoDatabase base;

	MongoConn() {
        mongoClient = new MongoClient();
        base = mongoClient.getDatabase("musicdb");
    }

	public void insereDoc(Document d, String collection) {
		MongoCollection doc = base.getCollection(collection);
		doc.insertOne(d);
	}

	public void delete(String mbid, String collection) {
        BasicDBObject del = new BasicDBObject();
        del.put("mbid", mbid);
        MongoCollection coll = base.getCollection(collection);
        System.out.println(coll.deleteOne(del));
    }

}
