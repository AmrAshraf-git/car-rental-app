package com.example.carrental.utility;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.carrental.model.User;

public class SessionManager {
    private static final String LOGIN_NAME="Authentication";
    private static final String LOGIN_KEY_EMAIL="Login_EMAIL";
    private static final String LOGIN_KEY_TOKEN="Login_TOKEN";
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

    public void saveLoginSession(User user){
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(LOGIN_KEY_EMAIL,user.getEmail());
        editor.putString(LOGIN_KEY_TOKEN,user.getToken());
        editor.apply();
    }

    public void removeLoginSession(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(LOGIN_KEY_EMAIL);
        editor.remove(LOGIN_KEY_TOKEN);
        editor.apply();
    }

    public User getLoginSession(){
        User user =new User();
        SharedPreferences sharedPreferences=context.getSharedPreferences(LOGIN_NAME,Context.MODE_PRIVATE);
        user.setEmail(sharedPreferences.getString(LOGIN_KEY_EMAIL,null));
        user.setToken(sharedPreferences.getString(LOGIN_KEY_TOKEN,null));
        return user;
    }

    public boolean isLoggedIn(){
        if(getLoginSession().getToken()!=null)
            return true;
        else
            return false;
    }

}
