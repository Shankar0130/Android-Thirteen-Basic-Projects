package com.shankaryadav.www.whatsappchatbox;

import java.io.Serializable;

public class Chat implements Serializable {
    public String id;
   public String name;
   public String about;
   public String imgurl;


   public Chat(){}


    public Chat(String id, String name, String about, String imgurl) {

        this.id = id;
        this.name = name;
        this.about = about;
        this.imgurl = imgurl;

    }

    public Chat(String id, String name, String about) {
        this.id = id;
        this.name = name;
        this.about = about;
    }

    public Chat(String name, String about) {
        this.name = name;
        this.about = about;
    }
}
