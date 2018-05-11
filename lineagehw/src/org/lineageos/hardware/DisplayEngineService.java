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

package com.android.server.display;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.Parcel;

public class DisplayEngineService {
    public static final int DE_ACTION_START = 0;
    public static final int DE_ACTION_STOP = 1;
    public static final int DE_ACTION_PAUSE = 2;
    public static final int DE_ACTION_RESUME = 3;
    public static final int DE_ACTION_FULLSCREEN_START = 4;
    public static final int DE_ACTION_FULLSCREEN_STOP = 5;
    public static final int DE_ACTION_FULLSCREEN_PAUSE = 6;
    public static final int DE_ACTION_FULLSCREEN_RESUME = 7;
    public static final int DE_ACTION_FULLSCREEN_EXIT = 8;
    public static final int DE_ACTION_THUMBNAIL = 9;
    public static final int DE_ACTION_FULLSCREEN_VIEW = 10;
    public static final int DE_ACTION_LIVE_IMAGE = 11;
    public static final int DE_ACTION_ONLINE_FULLSCREEN_VIEW = 12;
    public static final int DE_ACTION_IMAGE_EXIT = 13;
    public static final int DE_ACTION_ENTER = 14;
    public static final int DE_ACTION_EXIT = 15;
    public static final int DE_ACTION_MODE_ON = 16;
    public static final int DE_ACTION_MODE_OFF = 17;
    public static final int DE_ACTION_MAX = 18;

    public static final int DE_ACTION_PG_DEFAULT_FRONT = 10000;
    public static final int DE_ACTION_PG_BROWSER_FRONT = 10001;
    public static final int DE_ACTION_PG_3DGAME_FRONT = 10002;
    public static final int DE_ACTION_PG_EBOOK_FRONT = 10003;
    public static final int DE_ACTION_PG_GALLERY_FRONT = 10004;
    public static final int DE_ACTION_PG_INPUT_START = 10005;
    public static final int DE_ACTION_PG_INPUT_END = 10006;
    public static final int DE_ACTION_PG_CAMERA_FRONT = 10007;
    public static final int DE_ACTION_PG_OFFICE_FRONT = 10008;
    public static final int DE_ACTION_PG_VIDEO_FRONT = 10009;
    public static final int DE_ACTION_PG_LAUNCHER_FRONT = 10010;
    public static final int DE_ACTION_PG_2DGAME_FRONT = 10011;
    public static final int DE_ACTION_PG_MMS_FRONT = 10013;
    public static final int DE_ACTION_PG_VIDEO_START = 10015;
    public static final int DE_ACTION_PG_VIDEO_END = 10016;
    public static final int DE_ACTION_PG_CAMERA_END = 10017;
    public static final int DE_ACTION_PG_MAX = 10018;

    public static final int DE_SCENE_PG = 0;
    public static final int DE_SCENE_VIDEO = 1;
    public static final int DE_SCENE_VIDEO_HDR10 = 2;
    public static final int DE_SCENE_IMAGE = 3;
    public static final int DE_SCENE_CAMERA = 4;
    public static final int DE_SCENE_UI = 5;
    public static final int DE_SCENE_WEB = 6;
    public static final int DE_SCENE_WECHAT = 7;
    public static final int DE_SCENE_QQ = 8;
    public static final int DE_SCENE_TAOBAO = 9;
    public static final int DE_SCENE_POWERMODE = 10;
    public static final int DE_SCENE_AMBIENTLIGHT = 11;
    public static final int DE_SCENE_COLORTEMP = 12;
    public static final int DE_SCENE_SRE = 13;
    public static final int DE_SCENE_COLORMODE = 14;
    public static final int DE_SCENE_PROCAMERA = 15;
    public static final int DE_SCENE_EYEPROTECTION = 16;
    public static final int DE_SCENE_XNIT = 17;
    public static final int DE_SCENE_PG_EX = 18;
    public static final int DE_SCENE_BOOT_CMPL = 19;
    public static final int DE_SCENE_3D_COLORTMP = 20;
    public static final int DE_SCENE_RGLED = 21;
    public static final int DE_SCENE_BACKLIGHT_CHANGE = 22;
    public static final int DE_SCENE_HBM_BACKLIGHT = 23;
    public static final int DE_SCENE_MAX = 24;

    private static final String DESCRIPTOR = "com.huawei.displayengine.IDisplayEngineService";
    private static final int TRANSACTION_getSupported = 1;
    private static final int TRANSACTION_setScene = 2;
    private static final int TRANSACTION_getEffect = 4;
    private static final int TRANSACTION_setData = 3;
    private static final int TRANSACTION_setEffect = 5;

    private static IBinder sDisplayEngineService;

    static {
        sDisplayEngineService = ServiceManager.getService("DisplayEngineService");
    }

    public int setScene(int scene, int action) {
        if (sDisplayEngineService == null) {
            return -1;
        }

        try {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();

            data.writeInterfaceToken(DESCRIPTOR);
            data.writeInt(scene);
            data.writeInt(action);

            sDisplayEngineService.transact(TRANSACTION_setScene, data, reply, 0);

            return reply.readInt();
        } catch (Throwable t) {
            return -1;
        }
    }
}