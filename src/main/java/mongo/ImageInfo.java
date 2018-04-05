package mongo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoit
 */
public class ImageInfo {
    String size, url;

    public ImageInfo(String size, String url) {
        this.size = size;
        this.url = url;
    }

    @Override
    public String toString() {
        return "mongo.ImageInfo{" + "size=" + size + ", url=" + url + '}';
    }
    
    
}
