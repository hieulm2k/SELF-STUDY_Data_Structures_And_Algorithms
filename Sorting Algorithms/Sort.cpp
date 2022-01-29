#include "Sort.h"
using namespace std;

// Selection sort function
void SelectionSort(int a[], int n) {
	for (int i = 0; i < n-1; i++) {
		int temp = i;
		for (int j = i + 1; j < n; j++){
			if (a[j] < a[temp]) temp = j;
		}
		swap(a[temp], a[i]);
	}
}

// Insertion sort function
void InsertionSort(int a[], int n) {
	int i, j, x;
	for (i = 1; i < n; i++) {
		x = a[i];
		j = i - 1;
		while (j >= 0 && a[j] > x)
		{
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = x;
	}
}

// Binary insertion sort function
void BinaryInsertionSort(int a[], int n) {
	int i, j, mid, left, right, x;
	for (i = 1; i < n; i++) {
		x = a[i];
		left = 0;
		right = i - 1;
		while(left <= right) {
			mid = (left + right) / 2;
			if (a[mid] > x) right = mid - 1;
			else left = mid + 1;
		}
		for (j = i - 1; j >= left;  j--) {
			a[j + 1] = a[j];
		}
		a[left] = x;
	}
}

// Bubble sort function
void BubbleSort(int a[], int n) {
	for (int i = 0; i < n; i++) {
		for (int j = n - 1; j >= i; j--) {
			if (a[j - 1] > a[j]) swap(a[j - 1], a[j]);
		}
	}
}

// Shaker sort function
void ShakerSort(int a[], int n) {
	int Left = 0;
	int Right = n - 1;
	int temp = 0;
	while (Left < Right)
	{
		for (int i = Left; i < Right; i++)
		{
			if (a[i] > a[i + 1])
			{
				swap(a[i], a[i + 1]);
				temp = i;
			}
		}
		Right = temp;
		for (int i = Right; i > Left; i--)
		{
			if (a[i] < a[i - 1])
			{
				swap(a[i], a[i - 1]);
				temp = i;
			}
		}
		Left = temp;
	}
}

// Shell sort function
void ShellSort(int a[], int n) {
	for (int interval = n / 2; interval > 0; interval /= 2)
	{ 
		for (int i = interval; i < n; i ++)
		{
			int temp = a[i];
			int j;
			for (j = i; j >= interval && a[j - interval] > temp; j -= interval)
				a[j] = a[j - interval];
			a[j] = temp;
		}
	}
}

// Shift value function ( belong to Heap sort)
void Shift(int a[], int l, int n)
{
	int i, j;
	i = l;
	j = 2 * i + 1;
	while (j < n) {
		if (j + 1 < n) // Neu co 2 phan tu lien doi
			if (a[j + 1] > a[j]) j = j + 1; // Tim phan tu lien doi co gia tri lon hon
		if (a[j] < a[i]) break; // Neu thoa dieu kien lien doi thi dung lai
		else {
			swap(a[i], a[j]); // Doi cho cho vi tri dang xet va phan tu lien doi
			i = j; 
			j = 2 * i + 1; // Cap nhat lai vi tri de tiep tuc xet viec lan truyen
		}
	}
}

// Create heap function
void CreateHeap(int a[], int n){
	int l = (n / 2) - 1;
	while (l >= 0) {
		Shift(a, l, n);
		l--;
	}
}

// Heap sort function
void HeapSort(int a[], int n) {
	CreateHeap(a, n); // Khoi tao Heap
	while (n > 0) {
		swap(a[n-1], a[0]);
		n--;
		Shift(a, 0, n);
	}
}

void Merge(int a[], int first, int mid, int last)
{
	vector<int> tempArray; // Tao ra mang temp
	tempArray.resize(last + 1); // Khoi tao kich thuoc cho mang temp de luu lai gia tri

	int first1 = first; // Chi so bat dau mang thu nhat
	int last1 = mid; // Chi so ket thuc mang thu nhat
	int first2 = mid + 1; // Chi so bat dau mang thu hai
	int last2 = last; // Chi so ket thuc mang thu hai
	int index = first1; // Luu lai vi tri ton tai tiep theo trong mang temp

	// Khi nao chua ket thuc 2 mang thi lan luot sao chep gia tri nho hon trong hai gia tri dau tien cua 2 mang
	while ((first1 <= last1) && (first2 <= last2))
	{
		// Sau vong lap thi mang temp se duoc sap xep theo thu tu
		if (a[first1] <= a[first2])
		{
			tempArray[index] = a[first1];
			first1++;
		}
		else
		{
			tempArray[index] = a[first2];
			first2++;
		} 
		index++;
	} 

   // Chuyen het cac gia tri cua mang 1 vao temp neu chua ket thuc
	while (first1 <= last1)
	{
		tempArray[index] = a[first1];
		first1++;
		index++;
	}
   // Chuyen het cac gia tri mang 2 vao temp neu chua ket thuc
	while (first2 <= last2)
	{
	
		tempArray[index] = a[first2];
		first2++;
		index++;
	};
   // Sao chep cac gia tri cua mang temp vao mang ban dau, ta duoc 1 mang da duoc sap xep
	for (index = first; index <= last; index++)
		a[index] = tempArray[index];

}

// Merge sort function
void MergeSort(int a[], int l, int r)
{
	
	if (l < r)
	{
		int m = (l + r) / 2;

		// Sap xep lan luot 2 nua cua 1 mang
		MergeSort(a, l, m);
		MergeSort(a, m + 1, r);

		Merge(a, l, m, r); // Lan luot merge cac gia tri lai voi nhau ta duoc 1 mang dc sap xep theo thu tu
	}
}

// Sap xep cac phan tu o vi tri dau, giua, cuoi theo thu tu tang dan, ta duoc phan tu o giua la  median-of-three pivot selection.
void SortFirstMiddleLast(int a[], int first, int mid, int last) {
	if (a[first] > a[mid]) swap(a[first], a[mid]);
		
	if (a[mid] > a[last]) swap(a[last], a[mid]);
			
	if (a[first] > a[mid]) swap(a[first], a[mid]);
}

// Dung pivot de chia mang ra lam 2 phan tu first toi last
int Partition(int a[], int first, int last) {
	// Chon pivot va cap nhat lai vi tri pivot
	int mid = (last + first) / 2;
	SortFirstMiddleLast(a, first, mid, last);
	swap(a[mid], a[last - 1]);

	int pivotIndex = last - 1;
	int pivot = a[pivotIndex];

	// Khoi tao vi tri bat dau cua 2 mang S1 va S2
	int indexFromLeft = first + 1;
	int indexFromRight = last - 2;
	bool done = false;
	while (not done) {
	// Tang mang S1 neu phan tu ben trai nho hon pivot
		while (a[indexFromLeft] < pivot)
			indexFromLeft = indexFromLeft + 1;

	// Tang mang  S2 neu phan tu ben phai lon hon pivot
		while (a[indexFromRight] > pivot)
			indexFromRight = indexFromRight - 1;
				
		if (indexFromLeft < indexFromRight) {
			swap(a[indexFromLeft], a[indexFromRight]);
			indexFromLeft = indexFromLeft + 1;
			indexFromRight = indexFromRight - 1;
		}
		else
			done = true;
	}
	// Chuyen gia tri pivot vao giua 2 mang S1, S2 va cap nhat lai vi tri pivot
	swap(a[pivotIndex], a[indexFromLeft]);
	pivotIndex = indexFromLeft;
	return pivotIndex;
}

// Quick sort function
void QuickSort(int a[], int first, int last)
{
	if (first < last) {
		// Tao vach ngan: S1 | Pivot | S2
		int pivotIndex = Partition(a, first, last);
		// Goi lai ham voi 2 mang S1, S2
		QuickSort(a, first, pivotIndex - 1);
		QuickSort(a, pivotIndex + 1, last);
	}
} 

// Counting sort function
void CountingSort(int a[], int n) {
	vector<int> sortedList; // Khoi tao mang de luu cac gia tri duoc sap xep
	sortedList.resize(n);

	int max = a[0]; // Khoi tao gia tri lon nhat trong mang
	int min = a[0]; // Khoi tao gia tri nho nhat trong mang

	for (int i = 1; i < n; i++)
	{
		if (a[i] > max)
			max = a[i]; // Tim gia tri lon nhat trong mang
		else if (a[i] < min)
			min = a[i]; // Tim gia tri nho nhat trong mang
	}

	int k = max - min + 1; // Kich thuoc cua mang dung de dem

	vector<int> countArray; // Mang countArray de dem so lan xuat hien 
	countArray.resize(k);
	
	for (int i = 0; i < k; i++) countArray[i] = 0; // Lap day mang countArray bang gia tri 0

	for (int i = 0; i < n; i++)
		countArray[a[i] - min]++; // Luu lai so lan xuat hien cua moi gia tri

	// Chuyen cac gia tri cua countArray thanh vi tri chua cac so trong mang se duoc sap xep
	for (int i = 1; i < k; i++)
		countArray[i] += countArray[i - 1];


	// Dung 2 mang countArray va a de in ra mang sortedList cac gia tri tang dan
	for (int i = 0; i < n; i++)
	{
		sortedList[countArray[a[i] - min] - 1] = a[i];
		countArray[a[i] - min]--;
	}

	for (int i = 0; i < n; i++)
		a[i] = sortedList[i]; // Chuyen cac gia tri tu mang da duoc sap xep sang mang ban dau
}

// Ham counting sort dua vao chu so duoc dai dien boi exp
void countSort(int a[], int n, int exp)
{
	vector<int> outputList;
	outputList.resize(n);
	int i;
	int countDigit[10] = { 0 };

	// Luu lai so lan xuat hien cua chu so trong mang countDigit
	for (i = 0; i < n; i++)
		countDigit[(a[i] / exp) % 10]++;

	// Thay cac gia tri trong mang count thanh vi tri cua cac gia tri trong mang sortedList
	for (i = 1; i < 10; i++)
		countDigit[i] += countDigit[i - 1];

	// Xay dung mang sortedList
	for (i = n - 1; i >= 0; i--)
	{
		outputList[countDigit[(a[i] / exp) % 10] - 1] = a[i];
		countDigit[(a[i] / exp) % 10]--;
	}

	// Sao chep mang sortedList vao mang a, ta thu duoc mang a chua cac gia tri duoc sap xep theo chu so
	for (i = 0; i < n; i++)
		a[i] = outputList[i];
}


void RadixSort(int a[], int n)
{
	
	// Tim so luong lon nhat cua mang, tu do xac dinh so luong chu so
	int max = a[0];
	for (int i = 1; i < n; i++)
		if (a[i] > max)
			max = a[i];

	for (int exp = 1; max / exp > 0; exp *= 10)
		countSort(a, n, exp);
}

// flash sort function
int* FlashSort(int a[], int n)
{
	int max = 0, min = a[0];
	int m = (int)(0.45 * n);
	int* l = new int[m];

	for (int i = 1; i < n; ++i) {
		if (a[i] < min) {
			min = a[i];
		}
		if (a[i] > a[max]) {
			max = i;
		}
	}

	if (min == a[max]) {
		return a;
	}

	int c1 = (m - 1) / (a[max] - min);


	for (int k = 0; k < m; k++) {
		l[k] = 0;
	}
	for (int j = 0; j < n; ++j) {
		int k = (int)(c1 * (a[j] - min));
		++l[k];
	}

	for (int p = 1; p < m; ++p) {
		l[p] = l[p] + l[p - 1];
	}

	int hold = a[max];
	a[max] = a[0];
	a[0] = hold;

	//permutation
	int move = 0, t, flash;
	int j = 0;
	int k = m - 1;

	while (move < (n - 1)) {
		while (j > (l[k] - 1)) {
			++j;
			k = (c1 * (a[j] - min));
		}
		if (k < 0) break;
		flash = a[j];
		while (j != l[k]) {
			k = (c1 * (flash - min));
			hold = a[t = --l[k]];
			a[t] = flash;
			flash = hold;
			++move;
		}
	}

	//insertion
	for (j = 1; j < n; j++) {
		hold = a[j];
		int i = j - 1;
		while (i >= 0 && a[i] > hold) {
			a[i + 1] = a[i--];
		}
		a[i + 1] = hold;
	}
	delete[]l;
	return a;
}

// function count time running
double CountTime(int a[], int n, int k)
{
	clock_t start = clock();
	switch (k)
	{
	case 0:
		SelectionSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;

	case 1:
		InsertionSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 2:
		BinaryInsertionSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 3:
		BubbleSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 4:
		ShakerSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 5:
		ShellSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 6:
		HeapSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 7:
		MergeSort(a, 0, n - 1);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 8:
		QuickSort(a, 0, n - 1);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 9:
		CountingSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	case 10:
		RadixSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	default:
		a = FlashSort(a, n);
		return (double)((double)clock() - start) / CLOCKS_PER_SEC;
		break;
	}
	return 0;
}

void OuputFile(int a[], int n, int k, string fileName)
{
	string Alg;
	switch (k)
	{
	case 0:
		Alg = "SelectionSort";
		break;
	case 1:
		Alg = "InsertionSort";
		break;
	case 2:
		Alg = "BinaryInsertionSort";
		break;
	case 3:
		Alg = "BubbleSort";
		break;
	case 4:
		Alg = "ShakerSort";
		break;
	case 5:
		Alg = "ShellSort";
		break;
	case 6:
		Alg = "HeapSort";
		break;
	case 7:
		Alg = "MergeSort";
		break;
	case 8:
		Alg = "QuickSort";
		break;
	case 9:
		Alg = "CountingSort";
		break;
	case 10:
		Alg = "RadixSort";
		break;
	default:
		Alg = "FlashSort";
		break;
	}
	ofstream file(Alg + fileName + ".txt");
	switch (k)
	{
	case 0:
		SelectionSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;

	case 1:
		InsertionSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 2:
		BinaryInsertionSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 3:
		BubbleSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 4:
		ShakerSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 5:
		ShellSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 6:
		HeapSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 7:
		MergeSort(a, 0, n - 1);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 8:
		QuickSort(a, 0, n - 1);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 9:
		CountingSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	case 10:
		RadixSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	default:
		a = FlashSort(a, n);
		for (int i = 0; i < n; i++)
		{
			file << " " << a[i];
		}
		file.close();
		break;
	}
}