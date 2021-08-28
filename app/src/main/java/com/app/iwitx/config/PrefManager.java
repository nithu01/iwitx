package com.app.iwitx.config;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.app.iwitx.model.Data;



public class PrefManager {

    public static final String PREFS_NAME = "Selfmandi_data";
    private static final String SHARED_PREF_NAME = PREFS_NAME;
    private static final String TAG_TOKEN = "tagtoken";
    @SuppressLint("StaticFieldLeak")
    private static PrefManager mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;
    private static SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME ="Name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USERID = "UserId";
    private static final String KEY_ADDR_STATUS = "Wallet";
    private static final String KEY_KYC_STATUS= "Reffered";
    private static final String KEY_BANK_STATUS = "LastName";
    private static final String KEY_STATUS = "cashback";
    private static final String KEY_MOBILE = "Wallet";
    private static final String KEY_CREATED_DATE = "LastName";
    private static final String KEY_MAINW = "cashback";
    private static final String KEY_AEPSW = "UserId";
    private static final String KEY_MATMW = "Wallet";
    private static final String KEY_COMMW= "Reffered";
    private static final String KEY_DOB = "LastName";
    private static final String KEY_GENDER = "cashback";
    private static final String KEY_VILLCITY = "cashback";
    private static final String KEY_ADDRESS = "UserId";
    private static final String KEY_PINCODE = "Wallet";
    private static final String KEY_ADHARIMAGE= "Reffered";
    private static final String KEY_PANIMAGE = "LastName";
    private static final String KEY_PROFILEIMAGE = "cashback";
    private static final String KEY_CHEQUEIMAGE = "UserId";
    private static final String KEY_LASTLOGIN = "Wallet";
    private static final String KEY_AEPSSTATUS= "Reffered";
    private static final String KEY_AEPSKYCSTATUS = "LastName";
    private static final String KEY_USERTYPE = "LastName";

    public PrefManager(Context context) {
        mCtx = context;
        pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }


    public static synchronized PrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new
                    PrefManager(context);
        }
        return mInstance;
    }

    public Data getUserData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Data(sharedPreferences.getString(KEY_ID,null),
                sharedPreferences.getString(KEY_USERID,null),
                sharedPreferences.getString(KEY_USERTYPE,null),
                sharedPreferences.getString(KEY_KYC_STATUS,null),
                sharedPreferences.getString(KEY_ADDR_STATUS,null),
                sharedPreferences.getString(KEY_BANK_STATUS,null),
                sharedPreferences.getString(KEY_STATUS,null),
                sharedPreferences.getString(KEY_NAME,null),
                sharedPreferences.getString(KEY_MOBILE,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_CREATED_DATE,null),
                sharedPreferences.getString(KEY_MAINW,null),
                sharedPreferences.getString(KEY_AEPSW,null),
                sharedPreferences.getString(KEY_MATMW,null),
                sharedPreferences.getString(KEY_COMMW,null),
                sharedPreferences.getString(KEY_DOB,null),
                sharedPreferences.getString(KEY_GENDER,null),
                sharedPreferences.getString(KEY_VILLCITY,null),
                sharedPreferences.getString(KEY_ADDRESS,null),
                sharedPreferences.getString(KEY_PINCODE,null),
                sharedPreferences.getString(KEY_ADHARIMAGE,null),
                sharedPreferences.getString(KEY_PANIMAGE,null),
                sharedPreferences.getString(KEY_PROFILEIMAGE,null),
                sharedPreferences.getString(KEY_CHEQUEIMAGE,null),
                sharedPreferences.getString(KEY_LASTLOGIN,null),
                sharedPreferences.getString(KEY_AEPSSTATUS,null),
                sharedPreferences.getString(KEY_AEPSKYCSTATUS,null));
    }
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        // mCtx.startActivity(new Intent(mCtx, SplashMainActivity.class));
    }

}
