#include "wrapped_fs.h"
#define NUMBER_OF_ACTIONS 5

int nondet_int();

int main(){
  int d1,n,n_file;
  n = nondet_int(); //non_d directory
  init_fs(); //Init sample file-system

  //Assume to be in the allowed directory range
  __CPROVER_assume(n >= 0 && n < MAX_DIRS);
  // d1 = open_dir(n,"test");
  // assert(d1<0);

  ////////////////////////////////////////////////////////
  //A file can only be read or written when it is open  //
  ////////////////////////////////////////////////////////
  n_file = open_file(0, "test"); //open file
  assert(n_file == 0);
  // n_file = open_file(1, "test"); //open file
  // assert(n_file > 0);
  // n_file = open_file(2, "test"); //open file
  // assert(n_file > 0);
  // n_file = open_file(3, "test"); //open file
  // assert(n_file > 0);
  
  // if((file_status[n_file] & ENTRY_USED)){ //check if file is open
  //     int w1 = write_file(n_file, 0, 11, "hello world"); //write
  //     assert(w1 == 11); //Write should success file is open
  // }else{
  //     int w1 = write_file(n_file, 0, 11, "hello world"); //write
  //     assert(w1<0); //Write should fail file is closed
  // }
  // assert((dir_status[n] & ENTRY_USED) == (file_status[n] & ENTRY_USED)); //file is open
}

/*
void perform_action(int action){
  switch (action)
  {
  case 1:{ 
	  	n_file = open_file(n_dir, "test");
      assert((dir_status[n_dir] & ENTRY_USED) == (file_status[n_file] & ENTRY_USED)); //file is open
  	}
    break;
  case 2:{
  		int w1 = write_file(n_file, 0, 11, "hello world");
	  	if(file_status[n_file] & ENTRY_USED){
	  		assert(w1 == 11); //Write should success file is open
	  	}else{
			assert(w1<0); //Write should fail file is closed
	  	}
  	}
    break;
  case 3:{
	    char text[11];
	  	int r1 = read_file(n_file, 0, 11, &text);
	  	if(file_status[n_file] & ENTRY_USED){
	  		assert(r1 == 11); //Read should success file is open
	  	}else{
			assert(r1<0); //Read should fail file is closed
  		}
  	}
    break;
   case 4:{
	   	close_file(n_file); //close file
   		assert(!(file_status[n_file] & ENTRY_USED)); //check if file is closed
	}
   break;
   case 5:{
		n_dir = open_dir(n_dir, "test");
		if(dir_status[n_dir] & ENTRY_USED){ //directory is open
			assert(n_file == ERR_NOT_OPEN);
		  	assert(file_status[n_file] & ENTRY_USED); //file is open
		}else{
		  	assert(!(file_status[n_file] & ENTRY_USED)); //file should be closed
		}   }
   break;
  default:
    break;
  }
}
*/
