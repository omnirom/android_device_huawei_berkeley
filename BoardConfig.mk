#
# Copyright (C) 2017 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

include build/make/target/board/generic_arm64_a/BoardConfig.mk

DEVICE_PATH := device/huawei/berkeley

# Platform
TARGET_ARCH := arm64
TARGET_ARCH_VARIANT := armv8-a
TARGET_CPU_ABI := arm64-v8a
TARGET_CPU_ABI2 :=
TARGET_CPU_VARIANT := cortex-a73

TARGET_2ND_ARCH := arm
TARGET_2ND_ARCH_VARIANT := armv7-a-neon
TARGET_2ND_CPU_ABI := armeabi-v7a
TARGET_2ND_CPU_ABI2 := armeabi
TARGET_2ND_CPU_VARIANT := cortex-a53

PRODUCT_FULL_TREBLE := true
BOARD_VNDK_VERSION := current

# Assert
TARGET_OTA_ASSERT_DEVICE := berkeley,kirin970

# Kernel
BOARD_KERNEL_BASE := 0x00078000
BOARD_KERNEL_CMDLINE := loglevel=4 initcall_debug=n page_tracker=on unmovable_isolate1=2:192M,3:224M,4:256M printktimer=0xfff0a000,0x534,0x538
BOARD_KERNEL_IMAGE_NAME := Image.gz
BOARD_KERNEL_PAGESIZE := 2048
BOARD_KERNEL_TAGS_OFFSET := 0x07988000
BOARD_RAMDISK_OFFSET := 0x07B88000
BOARD_MKBOOTIMG_ARGS := --ramdisk_offset $(BOARD_RAMDISK_OFFSET) --tags_offset $(BOARD_KERNEL_TAGS_OFFSET)
TARGET_KERNEL_ARCH := arm64
TARGET_KERNEL_SOURCE := kernel/huawei/kirin970
TARGET_KERNEL_CONFIG := merge_kirin970_defconfig
TARGET_KERNEL_CROSS_COMPILE_PREFIX := aarch64-linux-android-

# Bluetooth
BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := $(DEVICE_PATH)/bluetooth
BOARD_HAVE_BLUETOOTH := true

TARGET_KERNEL_SOURCE := kernel/huawei/kirin970
TARGET_KERNEL_CONFIG := merge_kirin970_defconfig

# Partitions
BOARD_BOOTIMAGE_PARTITION_SIZE := 25165824
BOARD_SYSTEMIMAGE_PARTITION_SIZE := 5767168000

# Camera
BOARD_USES_SNAPDRAGONCAMERA_VERSION := 2

# Charger
HEALTHD_ENABLE_HUAWEI_FASTCHG_CHECK := true

# Properties
TARGET_SYSTEM_PROP := $(DEVICE_PATH)/system.prop

# Radio
TARGET_PROVIDES_QTI_TELEPHONY_JAR := true

# Recovery
TARGET_RECOVERY_FSTAB := $(DEVICE_PATH)/rootdir/etc/fstab.kirin970

# Release tools
TARGET_RELEASETOOLS_EXTENSIONS := $(DEVICE_PATH)/releasetools

# audio effects
TARGET_SYSTEM_AUDIO_EFFECTS := true

# SELinux
BOARD_PLAT_PRIVATE_SEPOLICY_DIR += $(DEVICE_PATH)/sepolicy/private
BOARD_PLAT_PUBLIC_SEPOLICY_DIR += $(DEVICE_PATH)/sepolicy/public
include vendor/omni/sepolicy/sepolicy.mk
