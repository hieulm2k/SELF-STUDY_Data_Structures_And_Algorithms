#include <iostream>
using namespace std;

// Declare struc node
template<class T>
struct Node
{
	T data;
	Node* pNext;
};

// Declare struc list
template<class T>
struct List
{
	Node<T>* pHead;
	Node<T>* pTail;
};

// Initialize list
template<class T>
void Init(List<T>& l)
{
	l.pHead = l.pTail = NULL;
}

// Create node from x
template<class T>
Node<T>* GetNode(T x)
{
	Node<T>* p = new Node<T>;
	p->data = x;
	p->pNext = NULL;
	return p;
}

// Add to head of list
template<class T>
void AddHead(List<T>& l, Node<T>* p)
{
	if (l.pHead == NULL) l.pHead = l.pTail = p;
	else {
		p->pNext = l.pHead;
		l.pHead = p;
	}
}

// Add to tail of list
template<class T>
void AddLast(List<T>& l, Node<T>* p)
{
	if (l.pHead == NULL) l.pHead = l.pTail = p;
	else {
		l.pTail->pNext = p;
		l.pTail = p;
	}
}

// Insert at specific position in list
template<class T>
void AddAt(List<T>& l, T x)
{
	T dat;
	int flag = 0;
	cout << "\nNhap vao gia tri can them: ";
	cin >> dat;
	Node<T>* p = GetNode(dat);
	if (l.pHead->data == x) AddHead(l, p);
	Node<T>* temp = l.pHead;
	while (temp != NULL && temp->pNext != NULL)
	{
		if (temp->pNext->data == x) {
			p->pNext = temp->pNext;
			temp->pNext = p;
			temp = temp->pNext;
			temp = temp->pNext;
		}
		else temp = temp->pNext;
	}
}

// Enter list
template<class T>
void InputList(List<T>& l, int& n)
{
	cout << "Nhap vao so luong phan tu cua danh sach lien ket: ";
	cin >> n;
	T x;
	for (int i = 0; i < n; i++)
	{
		cout << "Nhap vao gia tri can them: ";
		cin >> x;
		Node<T>* p = GetNode(x);
		AddLast(l, p);
	}
}

// Print list
template<class T>
void OutputList(List<T> l)
{
	if (l.pHead == NULL) cout << "\nNULL";
	for (Node<T>* p = l.pHead; p != NULL; p = p->pNext)
	{
		cout << " " << p->data;
	}
}

int main()
{
	int n;
	List<int> l;
	Init(l);
	InputList(l, n);
	OutputList(l);
	AddAt(l, 5);
	OutputList(l);
	system("pause");
}