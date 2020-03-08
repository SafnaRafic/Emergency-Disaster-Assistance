package org.SafnaRafic.emergencydisasterhelpline.models.dto;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;

import java.util.ArrayList;

public class BloodDonorData {
    public static ArrayList<BloodDonor> findByColumnAndValue(String column, String value, Iterable<BloodDonor> bloodDonors){
        ArrayList<BloodDonor> results=new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return (ArrayList<BloodDonor>)bloodDonors;
        }
        if(column.equals("all")){
            results=findByValue(value,bloodDonors);
            return results;
        }
        for(BloodDonor bloodDonor: bloodDonors){
            String aValue=getFieldValue(bloodDonor,column);
            if(aValue!=null && aValue.toLowerCase().equals(value.toLowerCase())){
                results.add(bloodDonor);
            }
        }
        return results;
    }
    public static String getFieldValue(BloodDonor bloodDonor,String fieldName){
        String theValue;
        if(fieldName.equals("zipcode")){
            theValue=bloodDonor.getZipcode();
        }else {
            theValue=bloodDonor.getBloodGroup().toString();
        }
        return theValue;
    }

    public static ArrayList<BloodDonor> findByValue(String value,Iterable<BloodDonor> bloodDonors){
        ArrayList<BloodDonor> results=new ArrayList<>();
        for(BloodDonor bloodDonor: bloodDonors){
            if(bloodDonor.getZipcode().contains(value)){
                results.add(bloodDonor);
            }else if(bloodDonor.getBloodGroup().toString().toLowerCase().contains(value.toLowerCase())){
                results.add(bloodDonor);
            }
        }
        return results;
    }
}
