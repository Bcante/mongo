package mongo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;


/**
 *
 * @author Benoit
 */
public class AlbumInfo {
    String name, artist, mbid, url;
    int released, listeners, playcounts;
    ArrayList<ImageInfo> images;
    ArrayList<LilTrackInfo> tracks;

    public AlbumInfo(String name, String artist, String mbid, String url, int released, int listeners, int playcounts, ArrayList<ImageInfo> imagesP, ArrayList<LilTrackInfo> tracksP) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;
        this.url = url;
        this.released = released;
        this.listeners = listeners;
        this.playcounts = playcounts;
        
        images = new ArrayList<>();
        tracks = new ArrayList<>();
        
        for (ImageInfo img : imagesP) {
            images.add(img);
        }
        for (LilTrackInfo lit : tracksP) {
            tracks.add(lit);
        }
    }

    @Override
    public String toString() {
        return "mongo.AlbumInfo{" + "name=" + name + ", artist=" + artist + ", mbid=" + mbid + ", url=" + url + ", released=" + released + ", listeners=" + listeners + ", playcounts=" + playcounts + ", images=" + images + ", tracks=" + tracks + '}';
    }
    public Document backToBson() {
            BufferedWriter writer = null;
                Document d = new Document();
                d.append("name", name);
                d.append("artist", artist);
                d.append("mbid", mbid);
                d.append("released", released);
                d.append("images", images);
                d.append("listeners", listeners);
                d.append("playcounts", playcounts);
                d.append("tracks", tracks);
                GsonBuilder builder = new  GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                String resParsable = gson.toJson(d);
                Document res = new Document();
                res = res.parse(resParsable);
            return res;
        }
   
    
    
}
