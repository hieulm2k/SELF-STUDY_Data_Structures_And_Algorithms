#pragma once
#include "nodeBST.h"
#include <iostream>
using namespace std;
NODE* CreateNode(int data);
void NLR(NODE* p_root);
void LNR(NODE* p_root);
void LRN(NODE* p_root);
NODE* Search(NODE* p_root, int x);
int Height(NODE* p_root);
void Insert(NODE*& p_root, int x);
void Remove(NODE*& p_root, int x);
bool IsBST(NODE* p_root);
int CountNode(NODE* p_root);