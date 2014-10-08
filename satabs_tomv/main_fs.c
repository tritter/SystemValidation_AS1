#include "wrapped_fs.h"
#include "fs.h"

//see wrapped_fs.h for test selection definitions
#define TEST_FILE_OPEN(x) (!( file_status[x] & ENTRY_USED == 0 ))
#define TEST_FILE_CLOSE(x) (file_status[x] & ENTRY_USED == 0 )
#define FILE_READ_OK(f,buff,len) (read_file(f,0,len,(void *)buff) == len)
#define FILE_READ_ERR(f,buff,len) (read_file(f,0,len,(void *)buff) < 0)

int nondet_int();
int init = 0;

int main(){
  
  #ifdef BOUNDS_CHECK
    int d1;
    int n = nondet_int();
    init_fs();
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
    __CPROVER_assume(opened==(0) || opened==(0 | ENTRY_USED) );
    int n = nondet_int();
    __CPROVER_assume(0 <= n && n < MAX_FILES);
    //set the open closed state for this file
    file_status[n] = opened;
    //assert wether read function only reads data when the file is open, and returns an error when the file is closed
    assert (  ( TEST_FILE_OPEN(n) && FILE_READ_OK(n,buff,10) ) || ( TEST_FILE_CLOSE(n) && FILE_READ_ERR(n,buff,10) ) );    
  #endif
  
  #ifdef FILE_OPEN
  #endif
  
  #ifdef DIR_TREE_OPEN
  #endif

}

