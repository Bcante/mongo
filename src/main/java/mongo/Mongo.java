package mongo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.BSON;
import org.bson.BSONObject;
import org.bson.Document;
import tools.HTTPTools;
import com.mongodb.DBObject;

import java.util.ArrayList;
import com.mongodb.util.JSON;
/**
 *
 * @author Benoit
 */
public class Mongo {
    Document d;
    static String key ="04ac1c16be9f023bb4a20096383deea9";
    final static HTTPTools ht = new HTTPTools();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Document d1 = trackGetInfo("deep purple", "smoke on the water");
        Document d2 = albumGetInfo("cher", "believe");
        MongoConn mongoConn = new MongoConn();
    }
    
    public static Document trackGetInfo(String nomArtiste, String nomTrack){
        String baseUrl = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key="+key+"&artist="+nomArtiste+"&track="+nomTrack+"&format=json";
        System.out.println(baseUrl);
        String res = ht.sendGet(baseUrl);
        System.out.println(res);
        return createTrack(res);
    }
    
    public static Document albumGetInfo(String nomArtiste, String nomAlbum) {
        String baseUrl = "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key="+key+"&artist="+nomArtiste+"&album="+nomAlbum+"&format=json";
        String res = ht.sendGet(baseUrl);
        return createAlbum(res);
    }
    
    /*
    
    */
    public static Document createTrack(String json) {
        TrackInfo ti;
        String name, mbid, url, artistName, artistMbid;
        int duration = -1;
        ArrayList<String> tags = new ArrayList<>();
        
        Document d = resetDoc(json,"track");
        name = d.get("name").toString();
        mbid = d.get("mbid").toString();
        url = d.get("url").toString();
        duration = Integer.parseInt(d.get("duration").toString());
        d = (Document)d.get("toptags");
        ArrayList<Document>  tagList= (ArrayList)d.get("tag");
        for (Document doc : tagList) {
            tags.add(doc.get("name").toString());
        }
        d = resetDoc(json,"track");
        
        d = (Document)d.get("artist");
        artistName = d.get("name").toString();
        artistMbid = d.get("mbid").toString();
        
        ti = new TrackInfo(name, mbid, url, artistName, artistMbid, duration, tags);
        return ti.backToBson();
    }
    
    public static Document createAlbum(String json) {
        AlbumInfo ai;
        Document d = resetDoc(json,"album");
        String name = d.get("name").toString();
        String artist = d.get("artist").toString();
        String mbid = d.get("mbid").toString();
        String url = d.get("url").toString();        
        int listeners = Integer.parseInt(d.get("listeners").toString());
        int playcount = Integer.parseInt(d.get("playcount").toString());
        
        d = (Document)d.get("wiki");
        String published[] = d.get("published").toString().split(" ");
        int released = Integer.parseInt(published[2].substring(0,4));
        
        d = resetDoc(json,"album");
        ArrayList<ImageInfo> listeImage = new ArrayList<>();
        ArrayList<Document> listTmp= (ArrayList)d.get("image");
        for (Document doc : listTmp) {
            String imgSize = (doc.get("size").toString());
            String imgUrl = (doc.get("#text").toString());
            listeImage.add(new ImageInfo(imgSize, imgUrl));
        }
        d = resetDoc(json,"album");
        ArrayList<LilTrackInfo> listeTracks = new ArrayList<>();
        d = (Document)d.get("tracks");
        listTmp = (ArrayList)d.get("track");
        for (Document doc : listTmp) {
            String trackNom = doc.get("name").toString();
            Document doc2 = new Document();
            doc2 = doc2.parse(doc.toJson());
            
            doc = (Document)doc.get("@attr");
            int trackRank = Integer.parseInt(doc.get("rank").toString());
            
            doc2 = (Document)doc2.get("artist");
            
            String trackMbid = doc2.get("mbid").toString();
            listeTracks.add(new LilTrackInfo(trackNom, trackMbid, trackRank));
        }
        
        ai = new AlbumInfo(name, artist, mbid, url, released, listeners, playcount, listeImage, listeTracks);
        return ai.backToBson();

    }
    
    public static Document resetDoc(String json,String cible) {
        Document d = new Document();
        d = d.parse(json);
        d = (Document)d.get(cible);
        return d;
    }
}
