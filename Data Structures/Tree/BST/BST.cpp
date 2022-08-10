#include "BST.h"

// Tao 1 node tu 1 gia tri cho truoc
NODE* CreateNode(int data) {
	// Tao ra node moi
	NODE* p = new NODE;
	p->p_left = NULL;
	p->p_right = NULL;
	p->key = data;
	return p;
}

// Xuat ra cay theo thao tac duyet truoc
void NLR(NODE* p_root)
{
	if (p_root != NULL)
	{
		cout << p_root->key << " ";
		NLR(p_root->p_left);
		NLR(p_root->p_right);
	}
}

// Xuat ra cay theo thao tac duyet giua
void LNR(NODE* p_root)
{
	if (p_root != NULL)
	{
		LNR(p_root->p_left);
		cout << p_root->key << " ";
		LNR(p_root->p_right);
	}
}

// Xuat ra cay theo thao tac duyet sau
void LRN(NODE* p_root)
{
	if (p_root != NULL)
	{
		LRN(p_root->p_left);
		LRN(p_root->p_right);
		cout << p_root->key << " ";
	}
}

// Tra ve 1 NODE co gia tri cho truoc tren cay nhi phan tim kiem
NODE* Search(NODE* p_root, int x)
{
	NODE* p = p_root;
	while (p != NULL)
	{
		// Neu tim thay thi tra ve node p
		if (p->key == x) return p; 

		// Neu x lon hon key cua parent thi tiep tuc xet voi con phai cua node parent
		else if (x > p->key) p = p->p_right; 

		// Nguoc lai thi xet x voi con trai cua node parent
		else p = p->p_left; 
	}
	return NULL;
}

// Tinh chieu cao cua cay nhi phan cho truoc
int Height(NODE* p_root)
{
	if (p_root == NULL) return 0;
	else {
		// Tinh chieu cao cua left subtree
		int lheight = Height(p_root->p_left);

		// Tinh chieu cao cua right subtree
		int rheight = Height(p_root->p_right);

		// Neu chieu cao left subtree > right subtree thi tang chieu cao cua left subtree len 1 don vi
		if (lheight > rheight) return (lheight + 1);

		// Nguoc lai thi tang chieu cao cua right subtree len 1 don vi
		else return (rheight + 1);	
	}
}

// Them 1 NODE co gia tri cho truoc vao cay nhi phan tim kiem
void Insert(NODE*& p_root, int x)
{
	// Neu tim thay node leaf thi tien hanh tao node
	if (p_root == NULL)
	{
		p_root = CreateNode(x);
		return;
	}	

	// Tim vi tri de them vao
	else
	{
		// Neu x < key cua node parent thi tiep tuc xet tren left subtree
		if (p_root->key > x) Insert(p_root->p_left, x);

		// Neu x > key cua node parent thi tiep tuc xet tren right subtree
		else if (p_root->key < x) Insert(p_root->p_right, x);
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

// Xoa 1 NODE co gia tri cho truoc tren cay
void Remove(NODE*& p_root, int x)
 {
	// Neu toi node leaf ma khong tim thay thi khong lam gi
	if (p_root == NULL) return;

	// Neu x < key cua node parent thi tiep tuc xet voi left child cua node parent
	if (p_root->key > x) Remove(p_root->p_left, x);

	// Neu x > key cua node parent thi tiep tuc xet voi right child cua node parent
	else if (p_root->key < x) Remove(p_root->p_right, x);

	// Tim thay node chua key x
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

// Dem so Node tren cay nhi phan cho truoc
int CountNode(NODE* p_root)
{
	if (p_root == NULL) return 0;
	else return 1 + CountNode(p_root->p_left) + CountNode(p_root->p_right);
}