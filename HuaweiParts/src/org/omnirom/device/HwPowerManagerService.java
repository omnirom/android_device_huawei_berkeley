/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server.power;

import android.content.Context;

public class HwPowerManagerService {
    public static native void init_native();
    public static native void finalize_native();

    public static native int nativeGetDisplayFeatureSupported(int feature);

    public static native String nativeReadColorTemperatureNV();
    public native int nativeSetColorTemperature(int color);
    public native int nativeUpdateRgbGamma(float r, float g, float b);

    private Context mContext;
    //private DisplayEffectMonitor mDisplayEffectMonitor;
    //private DisplayEngineManager mDisplayEngineManager;

    static {
        System.loadLibrary("hwpwmanager_jni");
    }

    public HwPowerManagerService(Context context) {
        //super(context);
        this.mContext = context;
        //this.mDisplayEffectMonitor = DisplayEffectMonitor.getInstance(this.mContext);
        //if (this.mDisplayEffectMonitor == null) {
        //    Slog.e(TAG, "getDisplayEffectMonitor failed!");
        //}

        init_native();

        //this.mDisplayEngineManager = DisplayModeControl.sDisplayEngineService;
    }
}
