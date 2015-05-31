package pz2015.habits.rmm;


import android.content.Context;
import android.content.SharedPreferences;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.List;

import pz2015.habits.rmm.others.Errors;
import pz2015.habits.rmm.others.JSONParser;

/**
 * Created by Marcin on 2015-05-08.
 * Manages sending and receiving data via JSON. Older versions of this class managed http POST.
 */
public class PostManagement {


    private static final String URL_SYNCHRO = "http://www.patra.waw.pl/php/synchro.php";
    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ERROR = "error";
    private static final String TAG_SALT = "salt";
    //URL to post
    private static String URL_LOGIN = "http://www.patra.waw.pl/php/check_login.php";
    private static String URL_REGISTER = "http://www.patra.waw.pl/php/register_user.php";
    Context context;
    List<NameValuePair> params;
    JSONObject json;
    //Creating JSON Parser object
    JSONParser jParser;

    public PostManagement(Context context, List<NameValuePair> params) {
        this.params = params;
        this.jParser = new JSONParser();
        this.json = null;
        this.context = context;
    }

    public Errors isUserExists() {
        // Get JSON OBJECT
        json = jParser.getJSONFromUrl(URL_LOGIN, params);

        if (json != null) {
            // success connection with server
            try {
                int success = json.getInt(TAG_SUCCESS);
                int errors = json.getInt(TAG_ERROR);

                if (success == 1) {
                    // USER FOUND

                    // Save our neu salt
                    String salt = json.getString(TAG_SALT);
                    // Set variables
                    SharedPreferences prefs = context.getSharedPreferences("rmm", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("salt", salt);

                    editor.commit();

                    return Errors.USER_FOUND;
                } else {
                    if (errors == 1) {
                        // BAD PASSWORD
                        return Errors.USER_BAD_PASSWORD;
                    } else if (errors == 2) {
                        // USER NOT EXISTS
                        // REJESTRACJA
                        return Errors.USER_NOT_EXISTS;
                    } else {
                        // Now its really bad...
                        return Errors.CRITICAL_ERROR;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // fail - json is null ?????
        return Errors.USER_JSON_IS_NULL;
    }

    public Errors registerNewUser() {
        // Get JSON OBJECT
        json = jParser.getJSONFromUrl(URL_REGISTER, params);

        if (json != null) {
            // success connection with server
            try {
                int success = json.getInt(TAG_SUCCESS);
                int errors = json.getInt(TAG_ERROR);

                //TODO POMYSLEC NAD INNYMI PRZYPADKAMI
                if (success == 1) {
                    // USER CREATED

                    // Save our neu salt
                    String salt = json.getString(TAG_SALT);
                    // Set variables
                    SharedPreferences prefs = context.getSharedPreferences("rmm", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("salt", salt);
                    editor.putBoolean("imBackAgain", true);

                    editor.commit();


                    return Errors.USER_CREATED;
                } else {
                    // Now its really bad...
                    return Errors.CRITICAL_ERROR;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // fail - json is null ?????
        return Errors.USER_JSON_IS_NULL;
    }

    public Errors synchro() {
        // Get JSON OBJECT
        json = jParser.getJSONFromUrl(URL_SYNCHRO, params);

        if (json != null) {
            // success connection with server
            try {
                int success = json.getInt(TAG_SUCCESS);
                int errors = json.getInt(TAG_ERROR);

                //TODO POMYSLEC NAD INNYMI PRZYPADKAMI
                if (success == 1) {
                    // Synchro is ok
                    String dupa = json.getString("dupa");
                    return Errors.SYNCHRO_OK;
                } else {
                    // Now its really bad...
                    return Errors.SYNCHRO_ERROR;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // fail - json is null ?????
        return Errors.SYNCHRO_ERROR;
    }

}
