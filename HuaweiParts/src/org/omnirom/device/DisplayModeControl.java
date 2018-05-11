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

import android.os.SystemProperties;
import android.util.Log;
import com.android.server.display.DisplayEngineService;
import com.android.server.display.DisplayEngineService_V1_0;
import com.android.server.display.DisplayEngineService_V1_1;
import com.android.server.HwSmartDisplayService;

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

    private static final String DISPLAY_ENGINE_V1_0_PROP = "init.svc.displayengine-hal-1-0";
    private static final String DISPLAY_ENGINE_V1_1_PROP = "init.svc.displayengine-hal-1-1";

    public static DisplayEngineService sDisplayEngineService;
    private static int sColorEnhancementCurrentMode;
    public static HwSmartDisplayService sHwSmartDisplayService;

    static {
        try {
            if (SystemProperties.get(DISPLAY_ENGINE_V1_0_PROP, "") != "") {
                sDisplayEngineService = new DisplayEngineService_V1_0();
            } else if (SystemProperties.get(DISPLAY_ENGINE_V1_1_PROP, "") != "") {
                sDisplayEngineService = new DisplayEngineService_V1_1();
            }

            sHwSmartDisplayService = new HwSmartDisplayService();
            sHwSmartDisplayService.init_native();

            sColorEnhancementCurrentMode = 0;
            sDisplayEngineService.setBootComplete(true);
            sDisplayEngineService.enablePowerMode(true);

            setMode(getDefaultMode());

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

    /*
     * Gets the preferred default mode for this device by it's
     * string identifier. Can return null if there is no default.
     */
    public static int getDefaultMode() {
        if (sDisplayEngineService == null) {
            return -1;
        }
        try {
            //int mode = Integer.parseInt(Utils.getPreference(getApplicationContext(), DeviceSettings.COLOUR_PROFILES_KEY, "0"));
            return 0;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}
