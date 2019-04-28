/*
* Copyright (C) 2017 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.omnirom.device;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.content.Intent;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.TwoStatePreference;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.util.Log;
import static android.provider.Settings.Secure.SYSTEM_NAVIGATION_KEYS_ENABLED;
import android.os.UserHandle;

public class GestureSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    public static final String KEY_PROXI_SWITCH = "proxi";
    public static final String KEY_FP_GESTURE_DEFAULT_CATEGORY = "gesture_settings";

    public static final String FP_GESTURE_SWIPE_DOWN_APP = "fp_down_swipe_gesture_app";
    public static final String FP_GESTURE_SWIPE_UP_APP = "fp_up_swipe_gesture_app";
    public static final String FP_GESTURE_SWIPE_RIGHT_APP = "fp_right_swipe_gesture_app";
    public static final String FP_GESTURE_SWIPE_LEFT_APP = "fp_left_swipe_gesture_app";
    public static final String FP_GESTURE_LONG_PRESS_APP = "fp_long_press_gesture_app";
    public static final String FP_GESTURE_TAP_APP = "fp_tap_gesture_app";

    public static final String DEVICE_GESTURE_MAPPING_0 = "device_gesture_mapping_0_0";
    public static final String DEVICE_GESTURE_MAPPING_1 = "device_gesture_mapping_1_0";
    public static final String DEVICE_GESTURE_MAPPING_2 = "device_gesture_mapping_2_0";
    public static final String DEVICE_GESTURE_MAPPING_3 = "device_gesture_mapping_3_0";
    public static final String DEVICE_GESTURE_MAPPING_4 = "device_gesture_mapping_4_0";
    public static final String DEVICE_GESTURE_MAPPING_5 = "device_gesture_mapping_5_0";
    public static final String DEVICE_GESTURE_MAPPING_6 = "device_gesture_mapping_6_0";
    public static final String DEVICE_GESTURE_MAPPING_7 = "device_gesture_mapping_7_0";
    public static final String DEVICE_GESTURE_MAPPING_8 = "device_gesture_mapping_8_0";
    public static final String DEVICE_GESTURE_MAPPING_9 = "device_gesture_mapping_9_0";
    public static final String DEVICE_GESTURE_MAPPING_10 = "device_gesture_mapping_10_0";
    public static final String DEVICE_GESTURE_MAPPING_11 = "device_gesture_mapping_11_0";
    public static final String DEVICE_GESTURE_MAPPING_12 = "device_gesture_mapping_12_0";
    public static final String DEVICE_GESTURE_MAPPING_13 = "device_gesture_mapping_13_0";
    public static final String DEVICE_GESTURE_MAPPING_14 = "device_gesture_mapping_14_0";

    private TwoStatePreference mProxiSwitch;
    private TwoStatePreference mFpSwipeDownSwitch;
    private AppSelectListPreference mFPDownSwipeApp;
    private AppSelectListPreference mFPUpSwipeApp;
    private AppSelectListPreference mFPRightSwipeApp;
    private AppSelectListPreference mFPLeftSwipeApp;
    private AppSelectListPreference mFPLongPressApp;
    private AppSelectListPreference mFPTapApp;
    private AppSelectListPreference mFPDTapApp;
    private PreferenceCategory fpGestures;
    private boolean mFpDownSwipe;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.gesture_settings, rootKey);

            mProxiSwitch = (TwoStatePreference) findPreference(KEY_PROXI_SWITCH);
            mProxiSwitch.setChecked(Settings.System.getInt(getContext().getContentResolver(),
            Settings.System.OMNI_DEVICE_PROXI_CHECK_ENABLED, 1) != 0);
        
            mFPRightSwipeApp = (AppSelectListPreference) findPreference(FP_GESTURE_SWIPE_RIGHT_APP);
            mFPRightSwipeApp.setEnabled(true);
            String value = Settings.System.getString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_12);
            mFPRightSwipeApp.setValue(value);
            mFPRightSwipeApp.setOnPreferenceChangeListener(this);

            mFPLeftSwipeApp = (AppSelectListPreference) findPreference(FP_GESTURE_SWIPE_LEFT_APP);
            mFPLeftSwipeApp.setEnabled(true);
            value = Settings.System.getString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_13);
            mFPLeftSwipeApp.setValue(value);
            mFPLeftSwipeApp.setOnPreferenceChangeListener(this);

            mFPLongPressApp = (AppSelectListPreference) findPreference(FP_GESTURE_LONG_PRESS_APP);
            mFPLongPressApp.setEnabled(true);
            value = Settings.System.getString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_14);
            mFPLongPressApp.setValue(value);
            mFPLongPressApp.setOnPreferenceChangeListener(this);
            
            mFPTapApp = (AppSelectListPreference) findPreference(FP_GESTURE_TAP_APP);
            mFPTapApp.setEnabled(true);
            value = Settings.System.getString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_8);
            mFPTapApp.setValue(value);
            mFPTapApp.setOnPreferenceChangeListener(this);
            
    }

    private boolean areSystemNavigationKeysEnabled() {
        return Settings.Secure.getInt(getContext().getContentResolver(),
               Settings.Secure.SYSTEM_NAVIGATION_KEYS_ENABLED, 0) == 1;
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mProxiSwitch) {
            Settings.System.putInt(getContext().getContentResolver(),
                    Settings.System.OMNI_DEVICE_PROXI_CHECK_ENABLED, mProxiSwitch.isChecked() ? 1 : 0);
            return true;
        }

        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mFPRightSwipeApp) {
            String value = (String) newValue;
            Settings.System.putString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_12, value);
        } else if (preference == mFPLeftSwipeApp) {
            String value = (String) newValue;
            Settings.System.putString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_13, value);
        } else if (preference == mFPLongPressApp) {
            String value = (String) newValue;
            Settings.System.putString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_14, value);
        } else if (preference == mFPTapApp) {
            String value = (String) newValue;
            Settings.System.putString(getContext().getContentResolver(), DEVICE_GESTURE_MAPPING_8, value);
        }
        return true;
    }


    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (!(preference instanceof AppSelectListPreference)) {
            super.onDisplayPreferenceDialog(preference);
            return;
        }
        DialogFragment fragment =
                AppSelectListPreference.AppSelectListPreferenceDialogFragment
                        .newInstance(preference.getKey());
        fragment.setTargetFragment(this, 0);
        fragment.show(getFragmentManager(), "dialog_preference");
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
