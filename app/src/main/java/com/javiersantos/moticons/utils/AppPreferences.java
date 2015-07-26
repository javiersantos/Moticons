package com.javiersantos.moticons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.securepreferences.SecurePreferences;

import java.util.HashSet;
import java.util.Set;

public class AppPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences secureSharedPreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor secureEditor;
    private Context context;

    public static final String KeyFirstRun = "prefFirstRun";
    public static final String KeyMoticoins = "prefMoticoins";
    public static final String KeyMoticonTimes = "prefMoticonTimes";
    public static final String KeyMoticonFavorites = "prefMoticonFavorites";
    public static final String KeyMoticonUnlocked = "prefMoticonUnlocked";

    public AppPreferences(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.secureSharedPreferences = new SecurePreferences(context, null, "moticons");
        this.editor = sharedPreferences.edit();
        this.secureEditor = secureSharedPreferences.edit();
        this.context = context;
    }

    public Boolean getFirstRun() {
        return sharedPreferences.getBoolean(KeyFirstRun, true);
    }

    public void setFirstRun(Boolean res) {
        editor.putBoolean(KeyFirstRun, res);
        editor.commit();
    }

    public Integer getMoticoins() {
        return secureSharedPreferences.getInt(KeyMoticoins, 0);
    }

    public void setMoticoins(Integer moticoins) {
        secureEditor.putInt(KeyMoticoins, moticoins);
        secureEditor.commit();
    }

    public Set<String> getMoticonTimes() {
        return secureSharedPreferences.getStringSet(KeyMoticonTimes, new HashSet<String>());
    }

    public void setMoticonTimes(Set<String> moticonTimes) {
        secureEditor.remove(KeyMoticonTimes);
        secureEditor.commit();
        secureEditor.putStringSet(KeyMoticonTimes, moticonTimes);
        secureEditor.commit();
    }

    public Set<String> getMoticonFavorites() {
        return secureSharedPreferences.getStringSet(KeyMoticonFavorites, new HashSet<String>());
    }

    public void setMoticonFavorites(Set<String> moticonFavorites) {
        secureEditor.remove(KeyMoticonFavorites);
        secureEditor.commit();
        secureEditor.putStringSet(KeyMoticonFavorites, moticonFavorites);
        secureEditor.commit();
    }

    public Set<String> getMoticonUnlocked() {
        return secureSharedPreferences.getStringSet(KeyMoticonUnlocked, new HashSet<String>());
    }

    public void setMoticonUnlocked(Set<String> moticonUnlocked) {
        secureEditor.remove(KeyMoticonUnlocked);
        secureEditor.commit();
        secureEditor.putStringSet(KeyMoticonUnlocked, moticonUnlocked);
        secureEditor.commit();
    }

}
