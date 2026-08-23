package com.Nikhil308.NikTube.Model;

public class VideoJSONData{

    public    long id;
    public   String  contentLink ;
    public   String name;
    public  String descrip ;

    public VideoJSONData(long id,String name,String descrip,String link){
        this.id=id;
        this.name=name;
        this.descrip=descrip;
        this.contentLink=link;
    }


}