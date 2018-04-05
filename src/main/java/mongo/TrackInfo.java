package mongo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.bson.Document;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.BasicBSONEncoder;
/**
 *
 * @author Benoit
 */
public class TrackInfo {
    String name, mbid, url;
    HashMap<String,String> artistTab;
    int duration = -1;
    ArrayList<String> tags = new ArrayList<>();
    public TrackInfo(String name, String mbid, String url, String artistName, String artistMbid, int duration, ArrayList<String> tagsP) {
        this.name = name;
        this.mbid = mbid;
        this.duration = duration;
        tags = new ArrayList<>();
        for (String s: tagsP) {
            this.tags.add(s);
        }
        artistTab = new HashMap<>();
        artistTab.put("artist",artistName);
        artistTab.put("mbid",artistMbid);
    } 

    @Override
    public String toString() {
        return "mongo.TrackInfo{" + "name=" + name + ", mbid=" + mbid + ", url=" + url + ", artistTab=" + artistTab + ", duration=" + duration + ", tags=" + tags + '}';
    }
    public Document backToBson() {
            Document d = new Document();
            d.append("name", name);
            d.append("mbid", mbid);
            d.append("duration", duration);
            d.append("artist", artistTab);
            d.append("tags", tags);
    return d;
    }
    
}
