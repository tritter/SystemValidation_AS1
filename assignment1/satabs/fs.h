#ifndef FS_H
#define FS_H

#define DIR_SIZE 128
#define FILE_SIZE 64

// Place holder type for directory data.
struct dir_data {
    char dummy[DIR_SIZE];
};

// Place holder type for file data.
struct file_data {
    char dummy[FILE_SIZE];
};

/* Initialize the file system and initialize the directory
   structure for the root folder.
   
   Must be called before any other operation.
   This root directory may never be closed.
 */
extern void fs_init_root(struct dir_data*ptr);

/* Test if a directory name exists in the parent directory.

   The structure parent must point to a valid open directory.
 */
extern int fs_exists_dir(struct dir_data*parent,char *name);

/* Create a new directory name in the directory parent
   and write the directory data to the area pointed to by ptr.

   The structure parent must point to a valid open directory.
 */
extern void fs_create_dir(struct dir_data*parent,char *name,struct dir_data*ptr);

/* Open an existing directory name in the directory parent
   and write the directory data to the area pointed to by ptr.

   The structure parent must point to a valid open directory.
 */
extern void fs_open_dir(struct dir_data*parent,char *name,struct dir_data*ptr);

/* Close a directory structure.

   When this function is called none of the children of this directory
   can be open.
 */
extern void fs_close_dir(struct dir_data*dir);


/* Test if the file name exists in the parent directory.

   The structure parent must point to a valid open directory.
 */
extern int fs_exists_file(struct dir_data*parent,char*name);

/* Create a new file name in the parent directory.

   The structure parent must point to a valid open directory.
 */
extern void fs_create_file(struct dir_data*parent,char *name,struct file_data*ptr);

/* Open an existing file name in the parent directory.

   The structure parent must point to a valid open directory.
 */
extern void fs_open_file(struct dir_data*parent,char *name,struct file_data*ptr);

/* Write data to file.

   The offset must be >= 0
   The length len must be >= 0
   The buffer must be at least len bytes 
   The structure file must point to a valid open file.
 */
extern void fs_write_file(struct file_data*file,long offset,int len,void*buffer);

/* Read data from file.

   The offset must be >= 0
   The length len must be >= 0
   The buffer must be at least len bytes 
   The structure file must point to a valid open file.
 */
extern void fs_read_file(struct file_data*file,long offset,int len,void*buffer);

/* Close the given file.

   The structure file must point to a valid open file.
 */
extern void fs_close_file(struct file_data*file);

#endif

