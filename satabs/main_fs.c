#include "wrapped_fs.h"

int nondet_int();

int main(){
  int d1;
  init_fs();
  d1=open_dir(2,"test");
  assert(d1<0);
}

