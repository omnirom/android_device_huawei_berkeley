/*
* Copyright (C) 2016 The OmniROM Project
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

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceViewHolder;
import android.database.ContentObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;

import com.android.server.power.HwPowerManagerService;

public class ColourTempPreference extends Preference implements
        SeekBar.OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private int mMinValue;
    private int mMaxValue;
    
    private HwPowerManagerService mHwPowerManager;


    public ColourTempPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMinValue = 0;
        mMaxValue = 255;
        setLayoutResource(R.layout.preference_seek_bar);
        mHwPowerManager = new HwPowerManagerService(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        int mOldStrength = getValue();
        mSeekBar = (SeekBar) holder.findViewById(R.id.seekbar);
        mSeekBar.setMax(255);
        mSeekBar.setProgress(mOldStrength);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public static boolean isSupported() {
        return true; //Utils.fileWritable(FILE_LEVEL);
    }

	public static int getValue() {
		return 128;
	}

	private void setValue(int newValue) {
	    mHwPowerManager.nativeSetColorTemperature(newValue);
	}

    public static void restore(Context context) {
        return;
    }

    public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromTouch) {
        setValue(progress);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        // NA
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        // NA
    }
}
