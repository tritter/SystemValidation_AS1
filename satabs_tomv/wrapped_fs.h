#ifndef WRAPPED_FS_H
#define WRAPPED_FS_H

//=================DEFINITION CHANGES=========================

//#define BOUNDS_CHECK             //checks if any arrays go out of bounds for part2.1
#define FILE_READ_WRITE        //checks if a file can only be read/written to if its opened for part2.2.a
//#define FILE_OPEN              //checks if a file can only be opened if its folder is opened
//#define DIR_TREE_OPEN          //check if a folder can only be opened if all its ancestors are open part2.2c

#define USE_BOUND_FIX            //fixes the out of bounds issues found by BOUNDS_CHECK

#define MAX_DIRS 3
#define MAX_FILES 6
#define ENTRY_USED 0x01

//=================END OF DEFINITION CHANGES==================

#define SUCCESS 0

#define ERR_NON_EXISTING -1
#define ERR_TOO_MANY_OPEN_DIRS -2
#define ERR_TOO_MANY_OPEN_FILES -3
#define ERR_EXISTS -4
#define ERR_NOT_OPEN -5
#define ERR_INVALID_ARG -6

//mss juist handig om dit niet globaal te maken, maar juist de checks in de functies zelf te doen?
//mss gaat het dan allemaal wat sneller
//=================ADDED GLOBAL VARIABLE DEFINITIONS=========================
extern static int    file_status[MAX_FILES];
extern static int    dir_status[MAX_DIRS];
//=================END OF ADDED GLOBAL VARIABLE DEFINITIONS=========================




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

