#include <stdio.h>
#include <stdlib.h>

long movimentos = 0;

void hanoi(int n, char origem, char destino, char aux)
{
	if(n > 0)
	{
		hanoi(n-1,origem, aux, destino);
		// printf("Mover %d de %c para %c\n",n,origem,destino);
		movimentos++;
		hanoi(n-1,aux,destino,origem);
	}
}

int main(int argc, char** argv)
{
	int n = atoi(argv[1]);
	hanoi(n,'A', 'B', 'C');
	printf("%ld\n",movimentos);
	return 0;
}