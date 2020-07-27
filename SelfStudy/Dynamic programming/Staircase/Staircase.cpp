// Programmer: Min Hie
// Last Modified: 23/07/2020
// Functional description: 
// The staircase comes up with n steps, suppose we only step m steps at a time,
// how many different ways are you going to get to the n floor? (m>0)

#include <iostream>
using namespace std;
#define m 3 // maximum step can climb

int num_ways_bottom_up(int n){
    if(n==0) return 1;
    int *a = new int[n+1];
    a[0] = 1;
    for(int i = 1; i <= n; ++i){
        int sum = 0;
        for(int j = 1; j <= m; ++j ){
            if(i-j >= 0) sum+=a[i-j];
        }
        a[i] = sum;
    }
    return a[n];
}

int main(){
    cout << "So cach de len la: " << num_ways_bottom_up(2) << endl;
    return 0;
}