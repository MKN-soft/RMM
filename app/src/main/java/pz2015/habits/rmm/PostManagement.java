package pz2015.habits.rmm;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2015-05-08.
 */
public class PostManagement extends AsyncTask<String, Integer, Double> {


    @Override
    protected Double doInBackground(String... params) {
// TODO Auto-generated method stub
        postData(params[0]);
        return null;
    }

    protected void onPostExecute(Double result){
        //TODO on post execute, what happens when we post (maybe message box?)
    }

    public void postData(String value) {
        //TODO change argument to habit object and send all of it's data at once in this method. What about server address and field names?
// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.88.140/php/baza.php");

        try {
// Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("myHttpData", value));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

// Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
