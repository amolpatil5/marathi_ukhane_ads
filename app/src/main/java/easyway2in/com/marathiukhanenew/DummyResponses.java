package easyway2in.com.marathiukhanenew;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Lenovo on 10/18/2015.
 */



    public class DummyResponses
    {
        private static DummyResponses instance = null;
        private JSONObject resposne = null;

        private DummyResponses(Context context) {
            try {
                InputStream in = context.getAssets().open("responses.json");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                char[] buffer = new char[1024 * 10];
                StringBuffer strBuffer = new StringBuffer(10000);
                int size = 0;
                int totalReadSize = size;
                while (true) {
                    size = br.read(buffer, 0, buffer.length);
                    totalReadSize += size;
                    strBuffer.append(buffer);
                    if (size == -1) {
                        break;
                    }
                }
                String jsonStr = strBuffer.toString();
                System.out.println("AMOL jsonStr -"+jsonStr);

                resposne = new JSONObject(jsonStr);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static DummyResponses getInstance(Context context) {
            if (instance == null) {
                instance = new DummyResponses(context);
            }
            return instance;
        }

        public JSONArray getResponseFor(String opCode)
        {
//            JSONObject toReturn = resposne.optJSONObject(opCode);
            JSONArray jarray = null;
            try {
                jarray = (JSONArray) resposne.getJSONArray(opCode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("AMOL toReturn -"+opCode+"rrrrrrr"+jarray);
            return jarray;
        }

    }





