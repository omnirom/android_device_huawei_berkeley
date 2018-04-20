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

PRODUCT_FULL_TREBLE := true
BOARD_VNDK_VERSION := current

# Assert
TARGET_OTA_ASSERT_DEVICE := berkeley,kirin970

# Bluetooth
BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := $(DEVICE_PATH)/bluetooth
BOARD_HAVE_BLUETOOTH := true

TARGET_KERNEL_SOURCE := kernel/huawei/kirin970
TARGET_KERNEL_CONFIG := merge_kirin970_defconfig

# Extended Filesystem Support
TARGET_EXFAT_DRIVER := exfat

# Partitions
BOARD_SYSTEMIMAGE_PARTITION_SIZE := 5767168000

# Camera
BOARD_USES_SNAPDRAGONCAMERA_VERSION := 2

# Properties
TARGET_SYSTEM_PROP := $(DEVICE_PATH)/system.prop

# Recovery
TARGET_RECOVERY_FSTAB := $(DEVICE_PATH)/rootdir/etc/fstab.kirin970

# Release tools
TARGET_RELEASETOOLS_EXTENSIONS := $(DEVICE_PATH)/releasetools

# SELinux
BOARD_PLAT_PRIVATE_SEPOLICY_DIR += $(DEVICE_PATH)/sepolicy/private
BOARD_PLAT_PUBLIC_SEPOLICY_DIR += $(DEVICE_PATH)/sepolicy/public
include vendor/omni/sepolicy/sepolicy.mk

#DEVICE_MATRIX_FILE := $(DEVICE_PATH)/compatibility_matrix.xml
