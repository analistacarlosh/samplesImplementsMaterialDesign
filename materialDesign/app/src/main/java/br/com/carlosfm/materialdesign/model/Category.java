package br.com.carlosfm.materialdesign.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.com.carlosfm.materialdesign.Http.AppHttp;

/**
 * Created by carlosfmr on 5/20/15.
 */
public class Category implements Serializable {

    public final int id;
    public final String description;
    public final String img;

    public static final String ENVIROMENT_URL = "http://apionibus.comercial.ws/bragmobi/";
    public static final String CATEGORY_URL_JSON = ENVIROMENT_URL + "category/";

    public Category(int id, String description, String img){
        this.id = id;
        this.description = description;
        this.img = img;
    }

    public static List<Category> searchCatagoryJson(){

        try{
            HttpURLConnection connecting = AppHttp.connect(CATEGORY_URL_JSON);
            int response = connecting.getResponseCode();

            Log.i("APPGUIA", "response connect:" + response);

            if(response == HttpURLConnection.HTTP_OK){
                InputStream is = connecting.getInputStream();
                JSONObject json = new JSONObject(AppHttp.bytesToString(is));
                Log.i("APPBUS", "searchCatagoryJson" + json);
                return readJsonCategory(json);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static List<Category> readJsonCategory(JSONObject json) throws JSONException{

        List<Category> listCategory = new ArrayList<Category>();
        JSONArray jsonCategory = json.getJSONArray("cateogry");

        Log.i("APPGUIA", "jsonCategory: " + jsonCategory.length());

        for(int counter = 0; counter < jsonCategory.length(); counter++){
            Log.i("APPGUIA", "readJsonCategory:" + counter);

            JSONObject objectItemCategory = jsonCategory.getJSONObject(counter);

            Category category = new Category(
                    objectItemCategory.getInt("id"),
                    objectItemCategory.getString("describe"),
                    objectItemCategory.getString("img")
            );

            listCategory.add(category);
        }

        return listCategory;
    }
}
