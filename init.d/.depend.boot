TARGETS = console-setup mountkernfs.sh resolvconf ufw apparmor plymouth-log hostname.sh screen-cleanup udev keyboard-setup mountdevsubfs.sh procps cryptdisks cryptdisks-early networking hwclock.sh iscsid urandom checkroot.sh lvm2 open-iscsi checkfs.sh mountall.sh checkroot-bootclean.sh kmod mountnfs.sh bootmisc.sh mountall-bootclean.sh mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
hwclock.sh: mountdevsubfs.sh
iscsid: networking
urandom: hwclock.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
open-iscsi: networking iscsid
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
mountnfs.sh: networking
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh udev mountnfs-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs-bootclean.sh: mountnfs.sh
