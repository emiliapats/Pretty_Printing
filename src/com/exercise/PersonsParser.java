package com.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpatil on 10/11/15.
 */
public class PersonsParser {

    public enum FORMAT {
        FORMAT1(1), FORMAT2(2), FORMAT3(3), INVALID(4);
        private int val;

        private FORMAT(int val) {
            this.val = val;
        }

        public Person generatePerson(final List<String> inputList) {
            Person person = new Person();
            if(this.val==1)
            {
                //Lastname, Firstname, (703)-742-0996, Blue, 10013
                person.setLastName(inputList.get(0));
                person.setFirstName(inputList.get(1));
                person.setPhoneNumber(inputList.get(2));
                person.setColor(inputList.get(3));
                person.setZipCode(inputList.get(4));
                return person;
            }
            else if(this.val==2)
            {
                //Firstname Lastname, Red, 11237, 703 955 0373

                List<String> convertedList = Arrays.asList(inputList.get(0).split(" "));
                person.setLastName(convertedList.get(1));
                person.setFirstName(convertedList.get(0));
                person.setPhoneNumber(inputList.get(3));
                person.setColor(inputList.get(1));
                person.setZipCode(inputList.get(2));
                return person;
            }
            else if(this.val==3)
            {
                //Firstname, Lastname, 10013, 646 111 0101, Green
                person.setLastName(inputList.get(1));
                person.setFirstName(inputList.get(0));
                person.setPhoneNumber(inputList.get(3));
                person.setColor(inputList.get(4));
                person.setZipCode(inputList.get(2));
                return person;
            }
            return null;
        }
    };


    public  FORMAT matchPhoneNumberAndNameReturnFormat(String phoneString)
    {
        if(phoneString!=null) {
            if (phoneString.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
                return FORMAT.FORMAT1;
            if (phoneString.matches("\\d{3}[\\s]\\d{3}[\\s]\\d{4}"))
                return FORMAT.FORMAT2;
        }
        return FORMAT.INVALID;
    }

    public String convertPhoneFormat1ToDefaultFormat(String phone)
    {
        String resultString=null;
        if(phone!=null) {
            if (!phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
                return null;
            StringBuilder sb = new StringBuilder(phone);
            sb.deleteCharAt(0).deleteCharAt(3);
            resultString = sb.toString();
        }
        return resultString;
    }
    public String convertPhoneFormat2ToDefaultFormat(String phone)
    {
        String resultString=null;
        if(phone!=null) {
            if (!phone.matches("\\d{3}[\\s]\\d{3}[\\s]\\d{4}"))
                return null;
            StringBuilder sb = new StringBuilder(phone);
            sb.replace(3, 4, "-");
            sb.replace(7, 8, "-");
            resultString = sb.toString();
        }
        return resultString;
    }

    public  FORMAT findFormatOfLine(final List<String> list)
    {
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i)!=null) {
                    FORMAT formatmatched = matchPhoneNumberAndNameReturnFormat(list.get(i).trim());
                    if (formatmatched == FORMAT.FORMAT1 && i == 2 && list.size() == 5)
                        return FORMAT.FORMAT1;
                    else if (formatmatched == FORMAT.FORMAT2 && (i == 3) && list.size() == 4) {
                        List<String> convertedList = null;
                        try {
                            convertedList = Arrays.asList(list.get(0).split(" "));
                        } catch (Exception e) {
                            return FORMAT.INVALID;
                        }
                        if (convertedList.size() == 2)
                            return FORMAT.FORMAT2;
                    } else if (formatmatched == FORMAT.FORMAT2 && (i == 3) && list.size() == 5)
                        return FORMAT.FORMAT3;
                }
            }
        }
        return FORMAT.INVALID;
    }

    public  Person parseString(String str) {

        List<String> convertedList = null;
        try {
            convertedList = Arrays.asList(str.split(","));

        }catch(Exception e)
        {
            return null;
        }
        if(convertedList.size()!=5&&convertedList.size()!=4)
        {
            return null;
        }
        FORMAT format = findFormatOfLine(convertedList);
        if(format==FORMAT.INVALID)
        {
            return null;
        }
        else if(format==FORMAT.FORMAT1)
        {
            convertedList.set(2, convertPhoneFormat1ToDefaultFormat(convertedList.get(2).trim()));
        }
        else if(format==FORMAT.FORMAT2||format==FORMAT.FORMAT3)
        {
            convertedList.set(3,convertPhoneFormat2ToDefaultFormat(convertedList.get(3).trim()));
        }
        Person person = format.generatePerson(convertedList);
        return person;

    }

}
