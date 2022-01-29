// Programmer: Min Hie
// Last Modified: 25/07/2020
// Functional description: 
// Use dynamic programming to find longest increasing 
// subsequence of array of N elements

#include <iostream>
#include <stack>
using namespace std;

void solve_bottom_up(int* a, int n) {
	int* L = new int[n];
	int* T = new int[n];
    int imax = 0;
	// Initialize an array L with N elements 1
	for (int i = 0; i < n; ++i)
		L[i] = 1;

	for (int i = 1; i < n; i++)
	{
		int jmax = 0;
		int check = 0;
		for (int j = 0; j < i; j++) {
			if (a[j] < a[i] && L[j] >= L[jmax]) {
				jmax = j;
				check = 1;
			}
		}
		if (check) {
			L[i] = L[jmax] + 1; // store length of increasing subsequence
			T[i] = jmax;		// store the element that stand behind
		}
        if(L[i] >= L[imax]) imax = i;
	}
    stack<int> s;
    while(L[imax] > 0){
        s.push(a[imax]);
        if(L[imax] == 1) break;
        imax = T[imax];
    }
    while(!s.empty()){
        cout << s.top() << " ";
        s.pop();
    }
}

int main(){
    int a[9] = {4,5,6,2,3,7,1,8,0};
    solve_bottom_up(a,9);
    return 0;
}