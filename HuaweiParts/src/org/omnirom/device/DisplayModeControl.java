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

package org.omnirom.device;

import android.util.Log;
import com.android.server.display.DisplayEngineService;
import com.android.server.display.DisplayEngineService_V1_1;
import com.android.server.HwSmartDisplayService;
import com.android.server.power.HwPowerManagerService;

import vendor.huawei.hardware.tp.V1_0.ExtTouchScreen;

/*
 * Display Modes API
 *
 * A device may implement a list of preset display modes for different
 * viewing intents, such as movies, photos, or extra vibrance. These
 * modes may have multiple components such as gamma correction, white
 * point adjustment, etc, but are activated by a single control point.
 *
 * This API provides support for enumerating and selecting the
 * modes supported by the hardware.
 */

public class DisplayModeControl {

    public static DisplayEngineService sDisplayEngineService;
    private static int sColorEnhancementCurrentMode;
    public static HwSmartDisplayService sHwSmartDisplayService;
    public static HwPowerManagerService mHwPowerManager;
    public static ExtTouchScreen mExtTouchScreen;

    static {
        try {
            sDisplayEngineService = new DisplayEngineService_V1_1();

            sHwSmartDisplayService = new HwSmartDisplayService();
            sHwSmartDisplayService.init_native();

            mHwPowerManager = new HwPowerManagerService();

            mExtTouchScreen = new ExtTouchScreen();

            sColorEnhancementCurrentMode = 0;

        } catch (Throwable t) {
            // Ignore, DisplayEngineService not available.
        }
    }


    /*
     * Get the name of the currently selected mode. This can return
     * null if no mode is selected.
     */
    public static int getCurrentMode() {
        if (sDisplayEngineService == null) {
            return -1;
        }
        return sColorEnhancementCurrentMode;
    }

    /*
     * Selects a mode from the list of available modes by it's
     * string identifier. Returns true on success, false for
     * failure. It is up to the implementation to determine
     * if this mode is valid.
     */
    public static boolean setMode(int mode) {
        if (sDisplayEngineService == null) {
            return false;
        }
        sColorEnhancementCurrentMode = mode;
        if (sColorEnhancementCurrentMode == 0) {
            sDisplayEngineService.enableColorMode(false);
        } else if (sColorEnhancementCurrentMode == 1) {
            sDisplayEngineService.enableColorMode(true);
        }

        return true;
    }
}
