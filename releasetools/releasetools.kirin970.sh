#!/sbin/sh

# Remount system as R/W
mount -o rw,remount /system

# Remove duplicated genfscon rules
sed -i "/genfscon exfat/d" /system/etc/selinux/plat_sepolicy.cil
sed -i "/genfscon fuseblk/d" /system/etc/selinux/plat_sepolicy.cil

# Add mapping for displayengine-hal-1.1
echo "(typeattributeset displayengineserver_27_0 (displayengineserver))" >> /system/etc/selinux/mapping/27.0.cil
echo "(expandtypeattribute (displayengineserver_27_0) true)" >> /system/etc/selinux/mapping/27.0.cil
echo "(typeattribute displayengineserver_27_0)" >> /system/etc/selinux/mapping/27.0.cil

exit 0
