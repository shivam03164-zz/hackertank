package com.hackertank.poolme;

import android.net.Uri;
import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mohammad.salim on 06/06/15.
 */
public class UserApi {
    public int responseCode=0;
    public String message;
    public String response;

    public HttpResponse postCall(String url, Map<String, String> keyValues) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        HttpResponse response = null;

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(keyValues.size());
            for(String key : keyValues.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, keyValues.get(key)));
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
        } catch (Exception e) {

        }
        // Execute HTTP Post Request
        return response;
    }

    public String getCall(String url, Map<String, String> params) throws UnsupportedEncodingException {
            // add parameters
            String combinedParams = "";
            if (params != null) {
                combinedParams += "?";
                for (String key : params.keySet()) {
                    String paramString = key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
                    if (combinedParams.length() > 1)
                        combinedParams += "&" + paramString;
                    else
                        combinedParams += paramString;
                }
            }
            HttpGet request = new HttpGet(url + combinedParams);
            // add headers
//            if (headers != null) {
//                headers = addCommonHeaderField(headers);
//                for (NameValuePair h : headers)
//                    request.addHeader(h.getName(), h.getValue());
//            }
            return executeRequest(request, url);

    }

    private String executeRequest(HttpUriRequest request, String url)
    {
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse;
        try
        {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);
                instream.close();
            }
        }
        catch (Exception e)
        { }
        return response;
    }

    private static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
        }
        catch (IOException e)
        { }
        return sb.toString();
    }

}
