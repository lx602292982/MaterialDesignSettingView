package com.spreawdwin.easyblur;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.orhanobut.logger.Logger;


/**
 * Created by lixiang on 2017/3/16.
 */

public class SettingFramgent extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


        CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager()
                .findPreference(getString(R.string.save_net_mode));


        CheckBoxPreference checkbox = (CheckBoxPreference) getPreferenceManager()
                .findPreference(getString(R.string.save_num_mode));

        Preference cachePref = getPreferenceManager().findPreference(getString(R.string.save_cache_mode));

        checkbox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                Logger.d("Pref " + preference.getKey() + " changed to " + newValue.toString() + "checked" + checked);
                return true;
            }
        });


        cachePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Logger.d(preference.getKey());
                return false;
            }
        });

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                Logger.d("Pref " + preference.getKey() + " changed to " + newValue.toString() + "checked" + checked);
                return true;

            }
        });
    }
}
