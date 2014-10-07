#include "wrapped_fs.h"
#include "fs.h"

static struct dir_data dirs[MAX_DIRS];
static int    dir_status[MAX_DIRS];

static struct file_data files[MAX_FILES];
static int    file_status[MAX_FILES];
static int    file_parent[MAX_FILES];

void init_fs(){
  //test (file - 1)
  //test/test (dir - 1)
  //     test (file - 2)
  //     /test/test (dir - 2)
  //           test (file - 3)
  //           test (file - 6)
  //           /test/test (dir - 3)
  //           test (file - 4)
  //           test (file - 5)


  int i;
  for(i=0;i<MAX_FILES;i++){
    file_status[i]=0;
    file_parent[i]=-1;
    if(i < MAX_DIRS){
        file_parent[i]=i;
        create_dir(i-1, "test");
        create_file(i,"test");
    }else{
        create_file(MAX_FILES-i, "test2");
    }
  }
  for(i=0; i<MAX_DIRS;i++){
    dir_status[i]=0;  
  }
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
    if (dir_status[parent]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    if (fs_exists_dir(&dirs[parent],name)){
        return ERR_EXISTS;
    }
    for(i=1;i<MAX_DIRS;i++){
        if (dir_status[i]&ENTRY_USED==0){
            dir_status[i]|=ENTRY_USED;
            fs_create_dir(&dirs[parent],name,&dirs[i]);
            return i;
        }
    }
    return ERR_TOO_MANY_OPEN_DIRS;
}

int close_dir(int id){
    int i;
    if (id < 0 || id > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    if (dir_status[id]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    for(i=0;i<MAX_FILES;i++){
        if (file_parent[i]==id){
            close_file(i);
        }
    }
    dir_status[i]&=~ENTRY_USED;
    fs_close_dir(&dirs[id]);
}

int open_file(int dir,char*name){
    int i;
    if (dir < 0 || dir > MAX_DIRS){
        return ERR_INVALID_ARG;
    }
    if (dir_status[dir]&ENTRY_USED==0){
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
    if (dir_status[dir]&ENTRY_USED==0){
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
    if (file_status[fd]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    fs_read_file(&files[fd],offset,len,buf);
    return len;
}

int write_file(int fd,long offset,int len,void*buf){
    if (fd < 0 || fd > MAX_FILES){
        return ERR_INVALID_ARG;
    }
    if (file_status[fd]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    fs_write_file(&files[fd],offset,len,buf);
    return len;
}

int close_file(int fd){
    if (fd < 0 || fd > MAX_FILES){
        return ERR_INVALID_ARG;
    }
    if (file_status[fd]&ENTRY_USED==0){
        return ERR_NOT_OPEN;
    }
    fs_file_close(&files[fd]);
    file_status[fd]&=~ENTRY_USED;
    file_parent[fd]=-1;
    return SUCCESS;
}

