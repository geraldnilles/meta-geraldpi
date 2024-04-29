#!/usr/bin/env bash

NVM_BLOCK_DEV=$( ls /dev/mmcblk*p1 )
BACKUP_DIR="/boot"
BACKUP_NAME="nvm_backup.tar.gz"

if ! mount -o ro ${NVM_BLOCK_DEV} ${BACKUP_DIR}
then
    echo "Failed to mount NV partition"
    exit 1
fi

# Check if the file list exists
if [ ! -f "${BACKUP_DIR}/${BACKUP_NAME}" ]
then
    echo "No Tarballs found to extract"
    umount ${BACKUP_DIR}  # Unmount since no updates needed
    exit 1
fi

# Extract tarball and overwrite the rootfs files
cd /
tar -xzf ${BACKUP_DIR}/${BACKUP_NAME}

umount ${BACKUP_DIR}

