#include <stdio.h>

void Move(int n,int col1, int col2)
{
	printf("Dia thu %d chuyen tu cot %d sang cot %d\n", n, col1, col2);
}

void HNTower(int n, int col1, int col2, int col3)
{
	if (n >0)
	{
		HNTower(n - 1, col1, col3, col2);
		Move(n, col1, col3);
		HNTower(n - 1, col2, col1, col3);
	}
}

int main()
{
	HNTower(4, 1, 2, 3);
}