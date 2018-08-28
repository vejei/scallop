package io.github.zeleven.scallop.ui.modules.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.base.BaseActivity;

public class SettingsActivity extends BaseActivity {
    private static SharedPreferences sharedPreferences;

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        getSupportActionBar().setTitle(R.string.toolbar_title_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            ListPreference languagePref = (ListPreference) findPreference("app_language");
            CharSequence[] entries = languagePref.getEntries();
            int index = languagePref.findIndexOfValue(
                    sharedPreferences.getString("app_language", "")
            );
            languagePref.setSummary(entries[index]);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            switch (s) {
                case "app_language":
                    ListPreference languagePref = (ListPreference) findPreference(s);
                    CharSequence[] entries = languagePref.getEntries();
                    String languageCode = sharedPreferences.getString("app_language", "zh_CN");
                    int index = languagePref.findIndexOfValue(languageCode);
                    CharSequence languageName = entries[index];
                    languagePref.setSummary(languageName);
                    break;
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }
    }
}
