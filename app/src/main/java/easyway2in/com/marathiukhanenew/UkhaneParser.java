package easyway2in.com.marathiukhanenew;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lenovo on 10/17/2015.
 */
public class UkhaneParser {
    String jsonstring;

    public ArrayList<String> getUkhaneDataForCategory(String categoryName) {
        ArrayList<String> ukhaneData;

        try {
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(jsonstring);
            ukhaneData = new ArrayList<String>();
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray(categoryName);

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

//                int id = Integer.parseInt(jsonObject.optString("id").toString());
//                String name = jsonObject.optString("name").toString();

                String name = jsonObject.toString();

//                float salary = Float.parseFloat(jsonObject.optString("salary").toString());
                ukhaneData.add(name);
//                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            return ukhaneData;
//            output.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();

        }
  return  null;
    }

}

