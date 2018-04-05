package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConn {

	public static void main(String[] args) {
		 MongoClient mongoClient = new MongoClient();
		 MongoDatabase base = mongoClient.getDatabase("tracks");
		 System.out.println("Affichage test du nom :");
		 System.out.println(base.getName());

	}

}
