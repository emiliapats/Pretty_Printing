package com.exercisetest;

import com.exercise.Person;
import com.exercise.PersonsParser;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpatil on 10/11/15.
 */
public class PersonsParserTest extends TestCase {
    public void testMatchPhoneNumberAndNameReturnFormatInValid1() throws Exception {
        PersonsParser parser  =new PersonsParser();
        PersonsParser.FORMAT format = parser.matchPhoneNumberAndNameReturnFormat("abcd");
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testMatchPhoneNumberAndNameReturnFormatInValid2() throws Exception {
        PersonsParser parser  =new PersonsParser();
        PersonsParser.FORMAT format = parser.matchPhoneNumberAndNameReturnFormat("");
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testMatchPhoneNumberAndNameReturnFormatInValid3() throws Exception {
        PersonsParser parser  =new PersonsParser();
        PersonsParser.FORMAT format = parser.matchPhoneNumberAndNameReturnFormat(null);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testMatchPhoneNumberAndNameReturnFormatFormat1() throws Exception {
        PersonsParser parser  =new PersonsParser();
        PersonsParser.FORMAT format = parser.matchPhoneNumberAndNameReturnFormat("(703)-742-0996");
        Assert.assertEquals(format, PersonsParser.FORMAT.FORMAT1);

    }
    public void testMatchPhoneNumberAndNameReturnFormatFormat2() throws Exception {
        PersonsParser parser  =new PersonsParser();
        PersonsParser.FORMAT format = parser.matchPhoneNumberAndNameReturnFormat("703 955 0373");
        Assert.assertEquals(format, PersonsParser.FORMAT.FORMAT2);

    }

    public void testFindFormatOfLineInValid1() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("703 955 0373");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineInValid2() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("703 955 0373");
        list.add("fname");
        list.add("703 955 0373");
        list.add("fname");
        list.add("703 955 0373");
        list.add("fname");
        list.add("703 955 0373");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineInValid3() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineInValid4() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = null;
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineInValid5() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add(null);
        list.add("703 955 0373");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineValidFormat1() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("lname");
        list.add("(703)-742-0996");
        list.add("color");
        list.add("zip");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.FORMAT1);
    }
    public void testFindFormatOfLineValidFormat2() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname lname");
        list.add("color");
        list.add("zip");
        list.add("703 955 0373");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.FORMAT2);
    }
    public void testFindFormatOfLineValidFormat3() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("lname");
        list.add("zip");
        list.add("703 955 0373");
        list.add("color");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.FORMAT3);
    }

    public void testFindFormatOfLineInValidFormat11() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("lname");
        list.add("703 955 0373");
        list.add("color");
        list.add("zip");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }
    public void testFindFormatOfLineInValidFormat12() throws Exception {
        PersonsParser parser  =new PersonsParser();
        List<String> list = new ArrayList<String>();
        list.add("fname");
        list.add("703 955 0373");
        list.add("color");
        list.add("zip");
        PersonsParser.FORMAT format = parser.findFormatOfLine(list);
        Assert.assertEquals(format, PersonsParser.FORMAT.INVALID);
    }



    public void testParseStringValidFormat1() throws Exception {
        PersonsParser parser  =new PersonsParser();
        Person person = parser.parseString("Lastname, Firstname, (703)-742-0996, Blue, 10013");
        Assert.assertEquals(person.getFirstName(),"Firstname");
        Assert.assertEquals(person.getLastName(),"Lastname");
        Assert.assertEquals(person.getPhoneNumber(),"703-742-0996");
        Assert.assertEquals(person.getColor(),"Blue");
        Assert.assertEquals(person.getZipCode(),"10013");
    }
    public void testParseStringValidFormat2() throws Exception {
        PersonsParser parser  =new PersonsParser();
        Person person = parser.parseString("Firstname Lastname, Red, 11237, 703 955 0373");
        Assert.assertEquals(person.getFirstName(),"Firstname");
        Assert.assertEquals(person.getLastName(),"Lastname");
        Assert.assertEquals(person.getPhoneNumber(),"703-955-0373");
        Assert.assertEquals(person.getColor(),"Red");
        Assert.assertEquals(person.getZipCode(),"11237");
    }
    public void testParseStringValidFormat3() throws Exception {
        PersonsParser parser  =new PersonsParser();
        Person person = parser.parseString("Firstname, Lastname, 10013, 646 111 0101, Green");
        Assert.assertEquals(person.getFirstName(),"Firstname");
        Assert.assertEquals(person.getLastName(),"Lastname");
        Assert.assertEquals(person.getPhoneNumber(),"646-111-0101");
        Assert.assertEquals(person.getColor(),"Green");
        Assert.assertEquals(person.getZipCode(),"10013");
    }

    public void testConvertPhoneFormat1ToDefaultFormatValid() throws Exception {
        PersonsParser parser  =new PersonsParser();
        String newPhoneFormat = parser.convertPhoneFormat1ToDefaultFormat("(703)-742-0996");
        Assert.assertEquals(newPhoneFormat,"703-742-0996");
    }
    public void testConvertPhoneFormat1ToDefaultFormatInValid() throws Exception {
        PersonsParser parser  =new PersonsParser();
        String newPhoneFormat = parser.convertPhoneFormat1ToDefaultFormat("(703-742-0996");
        Assert.assertEquals(newPhoneFormat,null);
    }

    public void testConvertPhoneFormat2ToDefaultFormatValid() throws Exception {
        PersonsParser parser  =new PersonsParser();
        String newPhoneFormat = parser.convertPhoneFormat2ToDefaultFormat("703 742 0996");
        Assert.assertEquals(newPhoneFormat,"703-742-0996");
    }
    public void testConvertPhoneFormat2ToDefaultFormatInValid() throws Exception {
        PersonsParser parser  =new PersonsParser();
        String newPhoneFormat = parser.convertPhoneFormat2ToDefaultFormat("(703)-742-0996");
        Assert.assertEquals(newPhoneFormat,null);
    }
}
