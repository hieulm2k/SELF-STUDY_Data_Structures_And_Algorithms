#include <iostream>
#include <string>
using namespace std;

string reverseInParentheses(string inputString) {
	
	int found = inputString.find(')'); // Luu lai vi tri tim thay dau ")"
	if (found == -1) return inputString; // Dieu kien dung cua de quy

	string temp;
	while (found >= 0 && inputString[found - 1] != '(') // Lap cho den khi found < 0 thi stop
	{
		temp.push_back(inputString[found - 1]); // Them 1 so ki tu tu o chuoi goc vao chuoi temp, chuoi temp la chuoi dao nguoc cua 1 so ki tu do
		found--;
	}
	inputString.replace(found - 1, temp.size() + 2, temp); // Thay the cac ki tu trong cap "( )" bang chuoi temp
	return reverseInParentheses(inputString); // De quy goi lai ham de tiep tuc xet cac cap "( )" khac
}

int main()
{
	string s = "foo(bar(baz))blim";
	string S;
	S = reverseInParentheses(s);
	cout << "Chuoi ban dau: " << s << endl << "Chuoi sau khi bo dau ngoac va dao tu: " << S << endl;
	system("Pause");
}