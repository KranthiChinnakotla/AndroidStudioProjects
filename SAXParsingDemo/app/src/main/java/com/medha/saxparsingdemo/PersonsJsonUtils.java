package com.medha.saxparsingdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/20/16.
 */
public class PersonsJsonUtils {
    static public class JsonParser{
        static ArrayList<Persons> parsePerspn(String in) throws JSONException {
            ArrayList<Persons> personList = new ArrayList<Persons>();
            JSONObject root = new JSONObject(in);
            JSONArray jsonPersonsArray = root.getJSONArray("persons");
                    for(int i=0; i< jsonPersonsArray.length();i++){
                        JSONObject personsJsonObject = jsonPersonsArray.getJSONObject(i);
                        Persons person = Persons.createperson(personsJsonObject);
                        /*person.setName(personsJsonObject.getString("name"));
                        person.setDepartment(personsJsonObject.getString("department"));
                        person.setAge(personsJsonObject.getInt("age"));
                        person.setId(personsJsonObject.getInt("id"));*/

                        personList.add(person);
                    }

            return personList;
        }
    }
}
