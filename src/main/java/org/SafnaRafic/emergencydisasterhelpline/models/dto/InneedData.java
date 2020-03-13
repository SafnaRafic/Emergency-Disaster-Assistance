package org.SafnaRafic.emergencydisasterhelpline.models.dto;

import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;

import java.util.ArrayList;

public class InneedData {
    public static ArrayList<Inneed> findByColumnAndValue(String column,String value,Iterable<Inneed> inneeds){
        ArrayList<Inneed> results=new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return (ArrayList<Inneed>)inneeds;
        }
        if(column.equals("all")){
            results=findByValue(value,inneeds);
            return results;
        }
        for(Inneed inneed: inneeds){
            String aValue=getFieldValue(column,inneed);
            if(aValue!= null && aValue.toLowerCase().contains(value.toLowerCase())){
                results.add(inneed);
            }
        }
        return results;
    }

    public static String getFieldValue(String fieldName,Inneed inneed){
        String theValue;

        if(fieldName.equals("zipcode")){
            theValue=inneed.getZipcode();
        }else if(fieldName.equals("city")){
            theValue=inneed.getCity();
        }else if(fieldName.equals("state")){
            theValue=inneed.getState();
        }else if(fieldName.equals("needs")){
            theValue=inneed.getNeeds().toString();
        }else
            theValue=inneed.getAddress();
        return theValue;
    }

    public static ArrayList<Inneed> findByValue(String value,Iterable<Inneed> inneeds) {
        ArrayList<Inneed> results = new ArrayList<>();
        for (Inneed inneed : inneeds) {
            if (inneed.getZipcode().contains((value.toLowerCase()))) {
                results.add(inneed);
            } else if (inneed.getCity().toLowerCase().contains(value.toLowerCase())) {
                results.add(inneed);
            } else if (inneed.getState().toLowerCase().contains(value.toLowerCase())) {
                results.add(inneed);
            } else if (inneed.getNeeds().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(inneed);
            }

        }
        return results;
    }

}
