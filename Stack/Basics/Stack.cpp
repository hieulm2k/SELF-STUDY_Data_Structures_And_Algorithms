#include <iostream>
#include <string>
#include <fstream>
using namespace std;

// Khai bao cau truc du lieu ngan xep Stack
struct Node
{
	string Data; // Du lieu chua ben trong Stack
	struct Node *pNext; // Con tro pNext de tao moi lien ket giua cac Node
};
typedef struct Node NODE;

struct Stack
{
	NODE* Top;// Top quan li dau Stack
};
typedef struct Stack STACK;

// Khoi tao ngan xep
void Init(STACK &s)
{
	s.Top = NULL;
}

// Tao Node trong ngan xep Stack
NODE* GetNode(string x) // Du lieu ben trong 1 Node dang la kieu String
{
	NODE* p = new NODE;
	if (p == NULL)
	{
		cout << "\n Khong du bo nho de cap phat!";
		system("pause");
		return NULL;
	}
	p->Data = x;
	p->pNext = NULL;
	return p;
}

// Kiem tra Stack rong, tra ve True neu Stack khong co phan tu, nguoc lai tra ve True
bool isEmpty(STACK s)
{
	if (s.Top == NULL) return true;
	return false;
}

// Them 1 Node vao Stack, tra ve True neu them thanh cong, nguoc lai tra ve False
bool Push(STACK &s, string x)
{
	NODE *p = new NODE;
	if (p == NULL)
	{
		cout << "Khong du bo nho de cap phat!";
		system("pause");
		return false;
	}
	p = GetNode(x);
	if (isEmpty(s))
	{
		s.Top = p;
	}
	p->pNext = s.Top;
	s.Top = p;
	return true;
}

// Lay phan tu trong Stack dem ra ngoai, tra ve True neu lay thanh cong, nguoc lai tra ve False
bool Pop(STACK& s, string x)
{
	if (isEmpty(s))
	{
		return false;
	}
	NODE* p = s.Top; // Con tro p tro toi Top
	x = p->Data;
	s.Top = s.Top->pNext;
	return true;
}

// Xem phan tu dau Stack, tra ve True neu xem thanh cong, nguoc lai tra ve False
bool Top(STACK s, string &x)
{
	if (isEmpty(s))
	{
		return false;
	}
	x = s.Top->Data;
	return true;
}

void Input(STACK& s)
{
	ifstream FileIn; // Khai bao File de doc du lieu
	FileIn.open("input.txt", ios_base::in); // Mo file de doc du lieu

	if (!FileIn)
	{
		cout << "Khong tim thay tap tin input.txt";
		system("pause");
		return;
	}

	string str; // Bien luu gia tri doc tu File vao
	getline(FileIn, str); // Doc du lieu tu file luu vao bien str;
	
	Init(s);

	for (int i = 0; i < str.length(); i++)
	{

	}



	FileIn.close();
}
void main()
{
	
	system("pause");
}