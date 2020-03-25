package org.SafnaRafic.emergencydisasterhelpline.models.dto;

import org.SafnaRafic.emergencydisasterhelpline.models.Volunteer;

import java.util.ArrayList;

public class VolunteerData {
    public static ArrayList<Volunteer> findByColumnAndValue(String column, String value, Iterable<Volunteer> volunteers){
        ArrayList<Volunteer> results=new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return (ArrayList<Volunteer>)volunteers;
        }
        if(column.equals("all")){
            results=findByValue(value,volunteers);
            return results;
        }
        for(Volunteer volunteer: volunteers){
            String aValue=getFieldValue(volunteer,column);
            System.out.println("return value from getField"+aValue.toLowerCase());
            System.out.println("Given value"+ value.toLowerCase()+"and aValue"+aValue.toLowerCase());
            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {

                System.out.println("inside if loop"+volunteer);
                results.add(volunteer);
            }
        }
        return results;
    }
    public static String getFieldValue(Volunteer volunteer,String fieldName){
        String theValue;
        if(fieldName.equals("zipcode")){
            theValue=volunteer.getZipcode();
        }else if(fieldName.equals("city")){
            theValue=volunteer.getCity();

        }else if(fieldName.equals("days")){
            theValue=volunteer.getDaysAvailability().toString();
            System.out.println("Inside getfield"+theValue);
        }else {
            theValue=volunteer.getState();

        }
        return theValue;
    }

    public static ArrayList<Volunteer> findByValue(String value,Iterable<Volunteer> volunteers){
        ArrayList<Volunteer> results=new ArrayList<>();
        for(Volunteer volunteer: volunteers){
            if(volunteer.getZipcode().contains(value.toLowerCase())){
                results.add(volunteer);
            }else if(volunteer.getDaysAvailability().toString().toLowerCase().contains(value.toLowerCase())){
                System.out.println(volunteer);
                results.add(volunteer);
            }else if(volunteer.getCity().toLowerCase().contains(value.toLowerCase())){
                results.add(volunteer);
            }else if(volunteer.getState().toLowerCase().contains(value.toLowerCase())){
                results.add(volunteer);
            }

        }
        return results;
    }
}
