package io.github.zeleven.scallop.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.zeleven.scallop.di.qualifier.ApplicationContext;

@Singleton
public class PreferencesHelper {
    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getStringPrefValue(String key) {
        return sharedPreferences.getString(key, "");
    }
}
