#ifndef WRAPPED_FS_H
#define WRAPPED_FS_H

#define SUCCESS 0

#define ERR_NON_EXISTING -1
#define ERR_TOO_MANY_OPEN_DIRS -2
#define ERR_TOO_MANY_OPEN_FILES -3
#define ERR_EXISTS -4
#define ERR_NOT_OPEN -5
#define ERR_INVALID_ARG -6

/* This function must be called to initialize the wrapper library.
   The root directory of the file system is opened as directory number 0.
 */
void init_fs();

/* Open an existing directory.
   returns the number of a directory on success (>=0)
   returns an error code in case of an error (<0)
 */
int open_dir(int parent,char*name);

/* Create a new directory.
   returns the number of a directory on success (>=0)
   returns an error code in case of an error (<0)
 */
int create_dir(int parent,char*name);

/* close a directory.
   returns 0 on success
   returns an error code in case of an error (<0)
 */
int close_dir(int id);

/* Open an existing file.
   returns a file descriptor on success (>=0)
   returns an error code in case of an error (<0)
 */
int open_file(int dir,char*name);

/* Create a new file file.
   returns a file descriptor on success (>=0)
   returns an error code in case of an error (<0)
 */
int create_file(int dir,char*name);


/* Read data from an open file.
   returns the number of bytes read on success (>=0)
   returns an error code in case of an error (<0)
 */
int read_file(int fd,long offset,int len,void*buf);

/* Write data to an open file.
   returns the number of bytes written on success (>=0)
   returns an error code in case of an error (<0)
 */
int write_file(int fd,long offset,int len,void*buf);

/* close a file.
   returns 0 on success
   returns an error code in case of an error (<0)
 */
int close_file(int fd);

#endif

