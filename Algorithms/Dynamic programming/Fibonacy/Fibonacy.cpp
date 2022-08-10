#include <iostream>
using namespace std;
#define MAX 100
// Use array
long Fibo1(int n){
    int F[MAX+1]; 
    F[0] = F[1] = 1;
    for(int i = 2; i <= n; ++i){
        F[i] = F[i-1] + F[i-2];
    }
    return F[n];
}

// Use 1 variable
int Fibo2(int n){
    int i = 1, F = 1, lastF = 1;
    while(i < n){
        F += lastF;
        lastF = F - lastF;
        i++;
    }
    return F;
}

int main()
{   
    cout << Fibo1(6) << endl;
    cout << Fibo2(6) << endl;
    system("pause");
    return 0;
}