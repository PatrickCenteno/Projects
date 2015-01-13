#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>

#include "numgen.h"

void randomGen (int size, int lower, int upper)
{
    std::fstream file("output.txt", std::fstream::out );

    if (!file.is_open())
        throw "File couldn't be opened";

	if(upper <= lower)	throw "Lower Boundary is greater than Upper";

	srand(time(NULL));
	int difference = upper - lower;

	for (int i = 0; i < size; i++)
		file << rand() % difference + lower << " ";

    file.close();
}
