package mongo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoit
 */
class LilTrackInfo {
    String name, mbid;
    int tracknumber;

    public LilTrackInfo(String name, String mbid, int tracknumber) {
        this.name = name;
        this.mbid = mbid;
        this.tracknumber = tracknumber;
    }

    @Override
    public String toString() {
        return "mongo.LilTrackInfo{" + "name=" + name + ", mbid=" + mbid + ", tracknumber=" + tracknumber + '}';
    }
    
    
}
