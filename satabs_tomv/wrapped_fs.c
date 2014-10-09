#include "wrapped_fs.h"
#include "fs.h"

//=================DEFINITION CHANGES=========================

//#define BOUNDS_CHECK             //checks if any arrays go out of bounds for part2.1
//#define FILE_READ_WRITE        //checks if a file can only be read/written to if its opened for part2.2.a
//#define FILE_CLOSE               //check correct operation of the function file_close
//#define FILE_OPEN              //checks if a file can only be opened if its folder is opened
//#define ROOT_CLOSE_CREATE          //check if root can be overwritten function works properly
#define DIR_TREE_OPEN          //check if a folder can only be opened if all its ancestors are open part2.2c

#define USE_BOUND_FIX            //fixes the out of bounds issues found by BOUNDS_CHECK

#define MAX_DIRS 3
#define MAX_FILES 6
#define ENTRY_USED 0x01

//FIX!! Error needed in case of close dir
#define ERR_CHILD_STILL_OPEN (-10)


//global variables

static struct dir_data dirs[MAX_DIRS];
static int    dir_status[MAX_DIRS];
//FIX!! dir parent needs to be stored, otherwise parents cannot close dirs when closed
static int    dir_parent[MAX_DIRS];

static struct file_data files[MAX_FILES];
static int    file_status[MAX_FILES];
static int    file_parent[MAX_FILES];


//see wrapped_fs.h for test selection definitions
#define TEST_DIR_OPEN(x)   ((dir_status[x] & ENTRY_USED) == ENTRY_USED )
#define TEST_DIR_CLOSE(x)  ((dir_status[x] & ENTRY_USED) == 0 )
#define TEST_FILE_OPEN(x)  ((file_status[x] & ENTRY_USED) == ENTRY_USED )
#define TEST_FILE_CLOSE(x) ((file_status[x] & ENTRY_USED) == 0 )
#define FILE_READ_OK(f,buff,len) (read_file(f,0,len,(void *)buff) == len)
#define FILE_READ_ERR(f,buff,len) (read_file(f,0,len,(void *)buff) < 0)

int nondet_int();
int init = 0;

int main(){
  
  //always initializer fs
  init_fs();
  
  #ifdef BOUNDS_CHECK
    int d1;
    int n = nondet_int();
    __CPROVER_assume(0 <= n && n < MAX_DIRS);
    d1=open_dir(n,"test");
    assert(d1<0); 
  #endif 
  
  #ifdef FILE_READ_WRITE
    
    //make a dummy buffer for file read/write
    char buff[1];
    
    //create a non deterministic file number for the whole file number range
    //craete a random open closed state for this file
    int opened = nondet_int();
    __CPROVER_assume(opened==0 || opened==ENTRY_USED);
    int file = nondet_int();
    __CPROVER_assume(0 <= file && file < MAX_FILES);
    
    //set the open/closed state for this file
    file_status[file] = nondet_int();
    file_status[file] |= opened;
    
    //call read function to test (assert statements added in function body)
    read_file(file,0,1,(void *)buff);
    
    //call write function to test (assert statements added in function body)
    write_file(file,0,1,(void *)buff);
        
  #endif
  
  #ifdef FILE_CLOSE
  
    //determine file and directory number non deterministically
    int file = nondet_int();
    __CPROVER_assume(0 <= file && file < MAX_FILES);
    
    //set the file to open
    file_status[file] |= ENTRY_USED;
    //test file open
    assert ( TEST_FILE_OPEN(file) );
    //close the file and test for succes and result
    assert ( (close_file (file) == SUCCESS) && TEST_FILE_CLOSE(file) );
    //test fail on second close 
    assert ( (close_file (file) != SUCCESS) && TEST_FILE_CLOSE(file) );
    
    //provide a non deterministic list of false arguments
    int fakeFile = nondet_int();
    __CPROVER_assume(-10 <= file && file < (2*MAX_FILES) && 0 > file && file >= MAX_FILES);
    //reopen file
    file_status[file] |= ENTRY_USED;
    //close a false file ID and test for unchanged file status
    assert ( (close_file (fakeFile) != SUCCESS) && TEST_FILE_OPEN(file) );
  
  #endif

  #ifdef FILE_OPEN

    //determine file and directory number non deterministically
    int file = nondet_int();
    __CPROVER_assume(0 <= file && file < MAX_FILES);
    int dir = nondet_int();
    __CPROVER_assume(0 <= dir && dir < MAX_DIRS);

    //set the parent of the file
    file_parent[file] = dir;
    
    //open file and directory
    file_status[file] |= ENTRY_USED;
    dir_status[dir] |= ENTRY_USED;

    //close dir and check file status is closed
    close_dir(dir);
    assert (TEST_FILE_CLOSE(file));
    
    //close the file and directory
    file_status[file] &= ~ENTRY_USED;
    dir_status[dir] &= ~ENTRY_USED;
    //check initial state
    assert (TEST_FILE_CLOSE(file));
    assert (TEST_DIR_CLOSE(dir));
    //check if file_open does not open the file
    open_file(dir,"test");
    assert (TEST_FILE_CLOSE(file));
    //check wether create file fails
    assert ( create_file(dir,"test") < 0 );

  #endif
  
  #ifdef ROOT_CLOSE_CREATE
    
    //test root is open
    assert ( TEST_DIR_OPEN(0) );
    //try to close root, this should fail
    assert ( close_dir(0) < 0 );
    assert ( TEST_DIR_OPEN(0) );
    
    int i;
    //try to create a number of folders, function should
    //never return zero
    for(i=0;i<MAX_DIRS;i++){
        assert ( create_dir(0,"test") != 0 );
    }
    
  #endif
  
  #ifdef DIR_TREE_OPEN
    int d_opened = nondet_int();
    __CPROVER_assume(d_opened==(0) || d_opened==(0 | ENTRY_USED) );
    int dir = nondet_int();
    __CPROVER_assume(0 <= dir && dir < MAX_DIRS);

    int dir1 = -1;
    int dir2 = -1;
    //create a dir with root as parent, and dir with this dir as parent
    while (dir1 <= 0) { dir1 = create_dir(0,"dir1"); }
    while (dir2 <= 0) { dir2 = create_dir(dir1,"dir1"); }
    //check for success
    assert ( TEST_DIR_OPEN(dir1) );
    assert ( TEST_DIR_OPEN(dir2) );
   
    //now close dir1 and check if dir 2 is closed properly
    close_dir (dir1);
    assert ( TEST_DIR_CLOSE(dir1) );
    assert ( TEST_DIR_CLOSE(dir2) );    
    
  #endif

}



void init_fs(){
  int i;
  
  //FIX!!
  #ifndef USE_BOUND_FIX
    for(i=0;i<MAX_FILES;i++){
      file_status[i]=0;
      file_parent[i]=-1;
      dir_status[i]=0;  
    }
  #else
    for(i=0; i<MAX_DIRS;i++){
      dir_status[i]=0;  
    }
    for(i=0;i<MAX_FILES;i++){
      file_status[i]=0;
      file_parent[i]=-1;
    }
  #endif
  
  dir_status[0]|=ENTRY_USED;
  fs_init_root(&dirs[0]);
}

int open_dir(int parent,char*name){
    int i;
    if (parent < 0 || parent > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    if (dir_status[parent]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    if (!fs_exists_dir(&dirs[parent],name)){
        return ERR_NON_EXISTING;
    }
    for(i=1;i<MAX_DIRS;i++){
        if (dir_status[i]&ENTRY_USED==0){
          
            //FIX!! dir_status was never set
            dir_status[i]|=ENTRY_USED;
            
            fs_open_dir(&dirs[parent],name,&dirs[i]);
            return i;
        }
    }
    return ERR_TOO_MANY_OPEN_DIRS;
}

int create_dir(int parent,char*name){
    int i;
    if (parent < 0 || parent > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    //FIX!! if (dir_status[parent]&ENTRY_USED==0){
    if ((dir_status[parent]&ENTRY_USED)==0){
        return ERR_NOT_OPEN;
    }
    if (fs_exists_dir(&dirs[parent],name)){
        return ERR_EXISTS;
    }
    
    for(i=0;i<MAX_DIRS;i++){
        //FIX!!  if (dir_status[i]&ENTRY_USED==0){
        if ((dir_status[i]&ENTRY_USED)==0){
            
            //FIX!! dir_status was never set
            dir_status[i]|=ENTRY_USED;
            //FIX!! need to store parent
            dir_parent[i] = parent;
            
            fs_create_dir(&dirs[parent],name,&dirs[i]);
            assert ( TEST_DIR_OPEN(i) );
            return i;
        }
    }
    return ERR_TOO_MANY_OPEN_DIRS;
}

int close_dir(int id){

    int i;

    //FIX!! if (id < 0 || id > MAX_DIRS){
    //root should not be touched
    if (id <= 0 || id > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    
    //FIX!! if (dir_status[id]&ENTRY_USED == 0){
    if ((dir_status[id]&ENTRY_USED) == 0){
        return ERR_NOT_OPEN;
    }
    
    //FIX!! need to check wether children of this dir are open, 
    //and return an error if so
    for(i=1;i<MAX_DIRS;i++){
        if ( (dir_parent[i]==id) && TEST_DIR_OPEN(i) ){
              return ERR_CHILD_STILL_OPEN;
        }
    }
    
    for(i=0;i<MAX_FILES;i++){
        if (file_parent[i]==id){
              close_file(i);
        }
    }
    
    //FIX!! dir_status was never set
    dir_status[id]&=~ENTRY_USED;
    
    fs_close_dir(&dirs[id]);
}

int open_file(int dir,char*name){
    int i;
    if (dir < 0 || dir > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    
    //FIX!!if (dir_status[dir]&ENTRY_USED==0){
    if ((dir_status[dir]&ENTRY_USED) == 0 ){
        return ERR_NOT_OPEN;
    }
    
    if (!fs_exists_file(&dirs[dir],name)){
        return ERR_NON_EXISTING;
    }
    
    i=0;
    while(file_status[i]&ENTRY_USED!=0){
        i++;
    }
    
    file_status[i]|=ENTRY_USED;
    file_parent[i]=dir;
    fs_open_file(&dirs[dir],name,&files[i]);
    return i;
}

int create_file(int dir,char*name){
    int i;
    if (dir < 0 || dir > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    
    //FIX!!if (dir_status[dir]&ENTRY_USED==0){
    if ((dir_status[dir]&ENTRY_USED)==0){
        return ERR_NOT_OPEN;
    }
    
    if (fs_exists_file(&dirs[dir],name)){
        return ERR_EXISTS;
    }
    
    i=0;
    while(file_status[i]&ENTRY_USED!=0){
        i++;
    }
    file_status[i]|=ENTRY_USED;
    file_parent[i]=dir;
    fs_create_file(&dirs[dir],name,&files[i]);
    return i;
}

int read_file(int fd,long offset,int len,void*buf){
  
    if (fd < 0 || fd > MAX_FILES){
        return ERR_INVALID_ARG;
    }
    
    //FIX!! if ( file_status[fd] & ENTRY_USED == 0 ){
    if ( (file_status[fd] & ENTRY_USED) == 0 ){
        //test if a file is closed before returning this error
        assert ( TEST_FILE_CLOSE(fd) );
        return ERR_NOT_OPEN;
    }
    
    //test if a file is open before actually reading it
    assert ( TEST_FILE_OPEN(fd) );
    
    fs_read_file(&files[fd],offset,len,buf);
    return len;
}

int write_file(int fd,long offset,int len,void*buf){
    if (fd < 0 || fd > MAX_FILES){
        return ERR_INVALID_ARG;
    }
    
    //FIX!! if (file_status[fd]&ENTRY_USED==0){
    if (  (file_status[fd] & ENTRY_USED) == 0 ){
        //test if a file is closed before returning this error
        assert ( TEST_FILE_CLOSE(fd) );
        return ERR_NOT_OPEN;
    }
    
    //test if a file is open before writing to it
    assert ( TEST_FILE_OPEN(fd) );
    
    fs_write_file(&files[fd],offset,len,buf);
    return len;
}

int close_file(int fd){
    if (fd < 0 || fd > MAX_FILES){
        return ERR_INVALID_ARG;
    }
    
    //FIX!! if (file_status[fd]&ENTRY_USED==0){
    if ( (file_status[fd]&ENTRY_USED ) ==0){
        return ERR_NOT_OPEN;
    }
        
    fs_file_close(&files[fd]);
    file_status[fd]&=~ENTRY_USED;
    file_parent[fd]=-1;
    return SUCCESS;
}

