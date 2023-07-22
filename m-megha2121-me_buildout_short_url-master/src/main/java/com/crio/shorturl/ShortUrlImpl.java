package com.crio.shorturl;
import java.util.HashMap;
import java.util.Random;
import java.util.*;

class ShortUrlImpl implements ShortUrl{
    String rm = "abcdefghijklmnopqrstuvwxyz0123456789";
    HashMap<String,String> map = new HashMap<String,String>();
    HashMap<String,Integer> map1 = new HashMap<String,Integer>();
    @Override
   public String registerNewUrl(String longUrl){
       char []randm = rm.toCharArray();
       int lm = rm.length();
       Random rand = new Random();
       String uni = "";
       for(int i=0;i<9;i++){
           char a = randm[rand.nextInt(lm)];
           uni+=a;
       }
       String shorturl="http://short.url/"+uni;
       if(map.containsKey(longUrl)){
           return map.get(longUrl);
       }
       else{
           map.put(longUrl,shorturl);
           return map.get(longUrl);
       }

    }
   @Override
   public String registerNewUrl(String longUrl, String shortUrl){
       for(Map.Entry<String,String>entry:map.entrySet()){
           if(entry.getValue().equals(shortUrl))
           {
               return null;
           }
       }
       map.put(longUrl,shortUrl);
       return shortUrl;
   }
   @Override
   public String getUrl(String shortUrl){
       for(Map.Entry<String,String>entry:map.entrySet())
       {
           if(entry.getValue().equals(shortUrl)){
               String s = entry.getKey();
               if(map1.containsKey(s)){
                   map1.put(s,map1.get(s)+1);
               }
               else{
                   map1.put(s,1);
               }
               return entry.getKey();
           }
       }
       return null;
   }
   @Override
   public Integer getHitCount(String longUrl){
       for(Map.Entry<String,Integer>entry1:map1.entrySet()){
           if(entry1.getKey().equals(longUrl)){
               return entry1.getValue();
           }
       }
       return 0;
   }
   @Override
   public String delete(String longUrl){
       if(map.containsKey(longUrl)){
           String dele = map.remove(longUrl);
           return dele;
       }
       else{
           return null;
       }
   }



}

