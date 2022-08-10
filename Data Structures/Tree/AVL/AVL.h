#pragma once
#include "NodeAVL.h"
#include <iostream>
using namespace std;

NODE* CreateNode(int data);
void Insert(NODE*& p_root, int x);
void Remove(NODE*& p_root, int x);
bool IsAVL(NODE* p_root);
void NLR(NODE* p_root);
void LNR(NODE* p_root);
void LRN(NODE* p_root);
void LevelOrder(NODE* p_root);