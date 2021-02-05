
import java.lang.Math;
  int power2(int n){
    int result = 0;
    int base = 2;
    //if n is 1, then it will return 1
    if(n == 1) { return 1;}
    for(int i = 1; i <= n; i++){
      //iterate n amounts of times and add the result to the power of 2 ^ n
      result = result + Math.pow(base,n);
    }
    return result;
  }
