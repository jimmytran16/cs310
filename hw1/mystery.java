
int mysterySum( int n )
 {
  int i, j, s=0;
  for(i=0; i < n; i++) {
    //  replacing the inner loop with a constant operation
    // now we can keep multiply i * i without having to iterate through j
    s+=(i*i);
  }
}
