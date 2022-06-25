package com.example.carrental.utility;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.carrental.model.SignInResponse;

public class SessionManager {
    private static final String LOGIN_NAME="Authentication";
    private static final String LOGIN_KEY_TOKEN="Login_TOKEN";
    private static final String LOGIN_KEY_ID="Login_ID";
    private static final String LOGIN_KEY_EMAIL="Login_EMAIL";
    private static final String LOGIN_KEY_FNAME = "Login_FNAME";
    private static final String LOGIN_KEY_LNAME = "Login_LNAME";
    private static final String LOGIN_KEY_PHONE = "Login_PHONE";
    private static SessionManager instance;
    private static Context context;

    public SessionManager(Context context) {
        SessionManager.context = context.getApplicationContext();
    }

    public static synchronized SessionManager getInstance(Context context){
        if(instance == null)
            instance=new SessionManager(context);
        return instance;
    }

    public void saveLoginSession(SignInResponse user){
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(LOGIN_KEY_TOKEN,user.getToken());
        editor.putString(LOGIN_KEY_ID,user.getId());
        editor.putString(LOGIN_KEY_EMAIL,user.getEmail());
        editor.putString(LOGIN_KEY_FNAME,user.getFirstName());
        editor.putString(LOGIN_KEY_LNAME,user.getLastName());
        editor.putString(LOGIN_KEY_PHONE,String.valueOf(user.getPhone()));
        editor.apply();
    }

    public void removeLoginSession(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(LOGIN_KEY_TOKEN);
        editor.remove(LOGIN_KEY_ID);
        editor.remove(LOGIN_KEY_EMAIL);
        editor.remove(LOGIN_KEY_FNAME);
        editor.remove(LOGIN_KEY_LNAME);
        editor.remove(LOGIN_KEY_PHONE);
        editor.apply();
    }

    public SignInResponse getLoginSession(){
        SignInResponse mUser =new SignInResponse();
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        mUser.setEmail(sharedPreferences.getString(LOGIN_KEY_EMAIL,null));
        mUser.setFirstName(sharedPreferences.getString(LOGIN_KEY_FNAME,null));
        mUser.setLastName(sharedPreferences.getString(LOGIN_KEY_LNAME,null));
        mUser.setPhone(Integer.parseInt(sharedPreferences.getString(LOGIN_KEY_PHONE,"0")));
        mUser.setToken(sharedPreferences.getString(LOGIN_KEY_TOKEN,null));
        mUser.setId(sharedPreferences.getString(LOGIN_KEY_ID,null));
        return mUser;
    }

    public boolean isLoggedIn(){
        return getLoginSession().getToken() != null;
    }

}
