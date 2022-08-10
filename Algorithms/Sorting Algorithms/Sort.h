#pragma once
#include <iostream>
#include <vector>
#include <time.h>
#include <fstream>
#include <iomanip>
#include <string.h>
using namespace std;

void SelectionSort(int a[], int n);
void InsertionSort(int a[], int n);
void BinaryInsertionSort(int a[], int n);
void BubbleSort(int a[], int n);
void ShakerSort(int a[], int n);
void ShellSort(int a[], int n);
void Shift(int a[], int l, int n);
void CreateHeap(int a[], int n);
void HeapSort(int a[], int n);
void Merge(int theArray[], int first, int mid, int last);
void MergeSort(int arr[], int l, int r);
void SortFirstMiddleLast(int a[], int first, int mid, int last);
int Partition(int a[], int first, int last);
void QuickSort(int a[], int first, int last);
void CountingSort(int a[], int n);
void countSort(int a[], int n, int exp);
void RadixSort(int a[], int n);
int* FlashSort(int a[], int n);
double CountTime(int a[], int n, int k);
void OuputFile(int a[], int n, int k, string filename);