package com.spreawdwin.easyblur;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatDelegate;

import com.orhanobut.logger.Logger;

import java.io.File;


/**
 * Created by lixiang on 2017/3/16.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference(getString(R.string.save_net_mode));
        CheckBoxPreference checkbox = (CheckBoxPreference) getPreferenceManager().findPreference(getString(R.string.save_num_mode));
        Preference cachePref = getPreferenceManager().findPreference(getString(R.string.save_cache_mode));
        cachePref.setSummary(cachePref.getSummary() + getCacheSize());

        cachePref.setOnPreferenceClickListener(this);

        checkbox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                Logger.d("Pref " + preference.getKey() + " changed to " + newValue.toString() + "checked" + checked);
                return true;
            }
        });

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                Logger.d("Pref " + preference.getKey() + " changed to " + newValue.toString() + "checked" + checked);
                if (checked) {
                    SharedPreferencesUtil.setBoolean(getActivity(), Constants.ISNIGHT, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    SharedPreferencesUtil.setBoolean(getActivity(), Constants.ISNIGHT, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                getActivity().recreate();
                return true;
            }
        });
    }

    public static void openAppInfo(Context context) {
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getApplicationContext().getPackageName()));
        context.startActivity(i);
    }


    private String getCacheSize() {
        File file = getActivity().getApplicationContext().getCacheDir();
        return MyUtils.getFileSize(file);
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        Logger.d(preference.getKey());
        openAppInfo(getActivity());
        return true;
    }
}
