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
import com.android.server.power.HwPowerManagerService;

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

    private static int sColorEnhancementCurrentMode;
    public static HwPowerManagerService mHwPowerManager;

    static {
        try {
            mHwPowerManager = new HwPowerManagerService();

            sColorEnhancementCurrentMode = 0;

        } catch (Throwable t) {
        }
    }


    /*
     * Get the name of the currently selected mode. This can return
     * null if no mode is selected.
     */
    public static int getCurrentMode() {
        return sColorEnhancementCurrentMode;
    }

    /*
     * Selects a mode from the list of available modes by it's
     * string identifier. Returns true on success, false for
     * failure. It is up to the implementation to determine
     * if this mode is valid.
     */
    public static boolean setMode(int mode) {
        sColorEnhancementCurrentMode = mode;
        return true;
    }
}