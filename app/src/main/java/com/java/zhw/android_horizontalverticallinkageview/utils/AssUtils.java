package com.java.zhw.android_horizontalverticallinkageview.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Eddy.
 * Description:
 */
public class AssUtils {
    public static final String tag = "AssUtils";

    public static JSONObject getAssetsJson(Context context, String jsonName) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open
                    (jsonName), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            return new JSONObject(stringBuilder.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
