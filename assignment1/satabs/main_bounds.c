#include "wrapped_fs.h"

int nondet_int();

int main(){
  int d1;
  int n = nondet_int();
  init_fs();
  __CPROVER_assume(0 <= n && n < MAX_DIRS);
  d1=open_dir(n,"test");
  assert(d1<0); 
}

