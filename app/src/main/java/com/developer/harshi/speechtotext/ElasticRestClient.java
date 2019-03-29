package com.developer.harshi.speechtotext;

import android.annotation.SuppressLint;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ElasticRestClient {

     //private static final String BASE_URL = "http://localhost:9200/"; //http://localhost:9200/
    // private static final String BASE_URL = "http://192.168.43.70:9200/";
    //private static final String BASE_URL = "http://10.0.2.2:9200/";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/todos/1";
    private static final String CLASS_NAME = ElasticRestClient.class.getSimpleName();
    private static final String TAG = "ElasticRestClient1";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    @SuppressLint("LongLogTag")
    private static String getAbsoluteUrl(String relativeUrl) {
        String s = BASE_URL + relativeUrl;
        Log.d(s, "getAbsoluteUrl: ");
        return BASE_URL + relativeUrl;
    }

    public void getHttpRequest() {
        try {
            Log.d(TAG, "getHttpRequest: tried out");

            ElasticRestClient.get("candidate/_doc/1", null, new JsonHttpResponseHandler() { // instead of 'get' use twitter/tweet/1
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    //this is being received
                    Log.i(CLASS_NAME, "onSuccess: 1" + response.toString());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.i(CLASS_NAME, "onSuccess: " + response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e(CLASS_NAME, "onFailure");
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    Log.i(CLASS_NAME, "onRetry " + retryNo);
                    // called when request is retried
                }
            });
        }
        catch (Exception e){
            Log.d(TAG, "getHttpRequest: 345");
            Log.e(CLASS_NAME, e.getLocalizedMessage());
        }
    }
}