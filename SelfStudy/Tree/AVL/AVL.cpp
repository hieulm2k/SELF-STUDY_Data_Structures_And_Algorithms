#include "AVL.h"
#include <math.h>
#include <iostream>
using namespace std;

// Khoi tao 1 NODE
NODE* CreateNode(int data)
{
	NODE* p = new NODE;
	p->key = data;
	p->p_left = p->p_right = NULL;
	p->height = 1;
	return p;
}

// Tim Max gia 2 so nguyen
int Max(int a, int b)
{
	if (a > b) return a;
	return b;
}

// Tinh chieu cao cua cay nhi phan cho truoc
int Height(NODE* p_root)
{
	if (p_root == NULL) return 0;
	return p_root->height;
}

// Ham xoay phai
NODE* rightRotate(NODE* p_root)
{
	NODE* new_root = p_root->p_left;
	NODE* right_subtree = new_root->p_right;

	// Thuc hien thao tac xoay
	new_root->p_right = p_root;
	p_root->p_left = right_subtree;

	// Cap nhat lai chieu cao
	p_root->height = Max(Height(p_root->p_left), Height(p_root->p_right)) + 1;
	new_root->height = Max(Height(new_root->p_left), Height(new_root->p_right)) + 1;

	// Tra ve nut moi tao;
	return new_root;
}

NODE* leftRotate(NODE* p_root)
{
	NODE* new_root = p_root->p_right;
	NODE* left_subtree = new_root->p_left;

	// Thuc hien thao tac xoay 
	new_root->p_left = p_root;
	p_root->p_right = left_subtree;

	// Cap nhat lai chieu cao;
	p_root->height = Max(Height(p_root->p_left), Height(p_root->p_right)) + 1;
	new_root->height = Max(Height(new_root->p_left), Height(new_root->p_right)) + 1;

	// Tra ve nut moi tao 
	return new_root;
}

// Lay chi so can bang cua 1 NODE 
int getBalance(NODE* p_root)
{
	if (p_root == NULL)
		return 0;
	return Height(p_root->p_left) - Height(p_root->p_right);
}

// Ham them 1 NODE co gia tri cho truoc vao cay AVL (Thong bao neu nhu gia tri cho truoc da co trong cay AVL)
void Insert(NODE*& p_root, int x)
{
	// Neu la Node leaf thi tien hanh them node
	if (p_root == NULL) {
		p_root = CreateNode(x);
		return;
	}
	if (x < p_root->key) Insert(p_root->p_left, x);
	else if (x > p_root->key) Insert(p_root->p_right, x);
	else {
		cout << "Node " << x << " already exists!" << endl;
		return;
	}

	// Cap nhat lai chieu cao cua p_root
	p_root->height = 1 + Max(Height(p_root->p_left), Height(p_root->p_right));

	// Lay chi so can bang de kiem tra xem cay co bi mat can bang hay khong
	int balance = getBalance(p_root);

	// Neu bi mat can bang thi co 4 truong hop co the xay ra:

	// Truong hop mat can bang theo kieu left - left
	if (balance > 1 && x < p_root->p_left->key) p_root = rightRotate(p_root);

	// Truong hop mat can bang theo kieu right - right  
	if (balance < -1 && x > p_root->p_right->key) p_root = leftRotate(p_root);

	// Truong hop mat can bang theo kieu left - right   
	if (balance > 1 && x > p_root->p_left->key)
	{
		p_root->p_left = leftRotate(p_root->p_left);
		p_root = rightRotate(p_root);
	}

	// Truong hop mat can bang theo kieu right - left   
	if (balance < -1 && x < p_root->p_right->key)
	{
		p_root->p_right = rightRotate(p_root->p_right);
		p_root = leftRotate(p_root);
	}
}

// Tim gia tri con trai nhat
int LeftMostValue(const NODE* root)
{
	int min = root->key;
	// Khi nao chua toi node leaf thi tiep tuc di chuyen ve left child
	while (root->p_left != NULL) {
		root = root->p_left;
		if (root->key < min) min = root->key;
	}

	// Tra ve gia tri cua con trai nhat
	return min;
}

// Tim gia tri con phai nhat
int RightMostValue(const NODE* root)
{
	int max = root->key;
	// Khi nao chua toi node leaf thi tiep tuc di chuyen ve left child
	while (root->p_left != NULL) {
		root = root->p_left;
		if (root->key > max) max = root->key;
	}

	// Tra ve gia tri cua con trai nhat
	return max;
}

// Xoa 1 NODE co gia tri cho truoc khoi cay AVL (Thong bao neu gia tri đo khong co trong cay AVL):
void Remove(NODE*& p_root, int x) {
	// Neu khong tim thay thi in ra va thoat chuong trinh
	if (p_root == NULL) {
		cout << x << " not found!";
		return;
	}

	// Neu x < gia tri node dang xet thi tiep tuc xet tren left subtree
	if (x < p_root->key) Remove(p_root->p_left, x);

	// Neu x > gia tri node dang xet thi tiep tuc xet tren right subtree
	else if (x > p_root->key) Remove(p_root->p_right, x);
	
	// Tim thay gia tri can xoa
	else {
		// Tao con tro de giu lai dia chi cua p_root
		NODE* p = p_root;

		// Chi co con phai
		if (p_root->p_left == NULL) p_root = p_root->p_right;

		// Chi co con trai
		else if (p_root->p_right == NULL) p_root = p_root->p_left;

		// Co du 2 con
		else {
			// Cap nhat gia tri cho p_root
			p_root->key = LeftMostValue(p_root->p_right);

			// Xoa not co gia tri nho nhat ben right subtree
			Remove(p_root->p_right, p_root->key);
			return;
		}

		// Xoa di node chua key x can tim
		delete p;
	}
	// Neu cay chi co 1 node thi return
	if (p_root == NULL) return;
	
	// Cap nhat lai chieu cao cho node hien tai
	p_root->height = 1 + Max(Height(p_root->p_left), Height(p_root->p_right));

	// Lay ra chi so can bang de kiem tra xem node co bi mat can bang khong
	int balance = getBalance(p_root);

	// Neu bi mat can bang thi co 4 truong hop co the xay ra:

	// Truong hop mat can bang theo kieu left - left
	if (balance > 1 && getBalance(p_root->p_left) >= 0)
		p_root = rightRotate(p_root);

	// Truong hop mat can bang theo kieu left - right
	if (balance > 1 && getBalance(p_root->p_left) < 0)
	{
		p_root->p_left = leftRotate(p_root->p_left);
		p_root = rightRotate(p_root);
	}

	// Truong hop mat can bang theo kieu right - right
	if (balance < -1 && getBalance(p_root->p_right) <= 0)
		p_root = leftRotate(p_root);

	// Truong hop mat can bang theo kieu right - left  
	if (balance < -1 && getBalance(p_root->p_right) > 0)
	{
		p_root->p_right = rightRotate(p_root->p_right);
		p_root = leftRotate(p_root);
	}

	return;
}

// Kiem tra xem 1 cay nhi phan cho truoc co phai la cay nhi phan tim kiem
bool IsBST(NODE* p_root)
{
	// Neu la cay rong thi tra ve true
	if (p_root == NULL)
		return true;

	// Kiem tra left subtree co phai la BST hay khong
	bool Left = IsBST(p_root->p_left);
	int MaxLeft, MinRight;

	// Co du 2 con
	if (p_root->p_left != NULL && p_root->p_right != NULL) {
		MaxLeft = RightMostValue(p_root->p_left);
		MinRight = LeftMostValue(p_root->p_right);
		if (MaxLeft > p_root->key || p_root->key > MinRight) return false;
	}

	// Chi co con phai
	else if (p_root->p_left == NULL && p_root->p_right != NULL)
	{
		MinRight = LeftMostValue(p_root->p_right);
		if (p_root->key > MinRight) return false;
	}

	// Chi co con trai
	else if (p_root->p_left != NULL && p_root->p_right == NULL)
	{
		MaxLeft = RightMostValue(p_root->p_left);
		if (MaxLeft > p_root->key) return false;
	}

	// Kiem tra right subtree co phai la BST hay khong
	bool Right = IsBST(p_root->p_right);

	// Kiem tra ket hop left subtree va right subtree, 1 trong 2 khong phai la BST thi tree do khong phai la BST
	return Left + Right;
}

// Kiem tra xem 1 cay nhi phan cho truoc co phai la cay AVL hay khong
bool IsAVL(NODE* p_root)
{
	if (p_root == NULL) return true;
	if (IsBST(p_root))
	{
		if (abs(getBalance(p_root)) <= 1 && IsAVL(p_root->p_left) && IsAVL(p_root->p_right)) return true;
	}
	return false;
}

// Xuat ra cay (gom key va height) theo thao tac duyet truoc
void NLR(NODE* p_root)
{
	if (p_root != NULL)
	{
		cout << "(" << p_root->key << "," << p_root->height << ") ";
		NLR(p_root->p_left);
		NLR(p_root->p_right);
	}
}

// Xuat ra cay(gom key va height) theo thao tac duyet giua
void LNR(NODE* p_root)
{
	if (p_root != NULL)
	{
		LNR(p_root->p_left);
		cout << "(" << p_root->key << "," << p_root->height << ") ";
		LNR(p_root->p_right);
	}
}

// Xuat ra cay(gom key va height) theo thao tac duyet sau
void LRN(NODE* p_root)
{
	if (p_root != NULL)
	{
		LRN(p_root->p_left);
		LRN(p_root->p_right);
		cout << "(" << p_root->key << "," << p_root->height << ") ";
	}
}

// Xuat ra cay (gom key va height) theo thao tac duyet tung tang
void PrintKeyHeight(NODE* p_root, int level)
{
	if (p_root == NULL) return;
	if (level == 1) cout << "(" << p_root->key << "," << p_root->height << ") ";
	else if (level > 1) {
		PrintKeyHeight(p_root->p_left, level - 1);
		PrintKeyHeight(p_root->p_right, level - 1);
	}
}

void LevelOrder(NODE* p_root) {
	int h = Height(p_root);
	for (int i = 1; i <= h; i++)
	{
		PrintKeyHeight(p_root, i);
		cout << endl;
	}
}