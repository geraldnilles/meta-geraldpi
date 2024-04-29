#!/usr/bin/env bash

NVM_BLOCK_DEV=$( ls /dev/mmcblk*p1 )
BACKUP_DIR="/boot"
BACKUP_NAME="nvm_backup.tar.gz"
FILES_LIST="${BACKUP_DIR}/nvm_backup_files.txt"
DEFAULT_FILES_LIST="/etc/nvm_backup_files.txt"
TEMP_TAR="/tmp/${BACKUP_NAME}"

# Initial read-only mount
if ! mount -o ro ${NVM_BLOCK_DEV} ${BACKUP_DIR}; then
    echo "Failed to mount NV partition (read-only)"
    exit 1
fi

# Check if the file list exists
if [ ! -f "${FILES_LIST}" ]; then
    echo "File list not found: ${FILES_LIST}"
    # Remount as RW
    umount ${BACKUP_DIR}
    mount  ${NVM_BLOCK_DEV} ${BACKUP_DIR}
    # Copy default file list into NVM
    cp  ${DEFAULT_FILES_LIST} ${FILES_LIST}
    sync
fi

echo "Creating Backup Tarbar"
# Create a new temporary tarball
tar -czf ${TEMP_TAR} -T ${FILES_LIST}

# Check if old and new tarballs are different
if ! diff ${TEMP_TAR} ${BACKUP_DIR}/${BACKUP_NAME} >/dev/null 2>&1; then

    echo "Writing System files to NVM"
    # Remount as RW
    umount ${BACKUP_DIR}  # Unmount it as RO
    mount  ${NVM_BLOCK_DEV} ${BACKUP_DIR}

    # Copy Temporary Tarball
    cp ${TEMP_TAR} ${BACKUP_DIR}/${BACKUP_NAME}
    sync
else
    echo "No changes observed"
fi

# Cleanup
rm -f ${TEMP_TAR}
umount ${BACKUP_DIR}

