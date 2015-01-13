#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <algorithm>

#include "array.h"
#include "integer.h"
#include "numgen.h"
#include "Timer.h"

const int SIZE = 10000,
          SIZE_2 = 1000,
          NUMGEN_SIZE = 10000;

bool linearSearch(int val, Array<Integer> &array);
bool binarySearch(int val, Integer begin, Integer end, Array<Integer> &array);
void simpleSort(Array<Integer> &array, int size);

int main()
{
    //First Array
    Array<Integer> array(SIZE);
    int x;

    try { randomGen(NUMGEN_SIZE, 1, 500); }
    catch(const char *e)    {std::cout << e;}

    //First input and output test
    std::fstream readIn("output.txt", std::fstream::in);

    if (readIn.is_open())
        std::cout << "File opened Correctly" << std::endl;


    std::cout << std::endl;
    std::cout << "\t***** First Array *****" << std::endl;
    std::cout << std::endl;

        try{
            for (int i = 0; i < NUMGEN_SIZE; i++){
                readIn >> x;
                Integer integer(x);
                array[i] = integer;
            }

            for (int i = 0; i < NUMGEN_SIZE; i++)
                std::cout << array[i];
        }
        catch(ArrayException e) {std::cout << e;}

    Timer t, t2, t3;
    t.start();
        simpleSort(array, array.getSize());
    t.stop();

    std::cout << std::boolalpha;
    std::cout << array << std:: endl;
    std::cout << "The time to sort the first array is " << t << std::endl;

    t2.start();
    std::cout << "Searching for 350 in array comes up: " << linearSearch(350, array) << std::endl;
    t2.stop();
    std::cout << "The time for a linear seach is " << t2 << std::endl;

    try{
    t3.start();
    std::cout << "Searching for 350 again in an array comes up: " << binarySearch(350, array[0], array[SIZE - 1], array) << std::endl;
    t3.stop();

    std::cout << "The time for a binary search is " << t3 << std::endl;
    } catch(ArrayException e){
        std::cout << e << std::endl;
    } catch(const char * e) {
        std::cout << e << std::endl;
    }


    /*
    Timer t2;

    //Second Array
    Array<Integer> array2(SIZE_2);
    try { randomGen(SIZE_2, 1, 1000);    }
    catch(const char *e)    {std::cout << e;}

    //Second Test
    std::fstream readIn2("output.txt", std::fstream::in);

    if(readIn2.is_open())
        std::cout << "File is open" << std::endl;

    std::cout << std::endl;
    std::cout << "\t***** Second Array *****" << std::endl;
    std::cout << std::endl;

        try{
            for (int i = 0; i < SIZE_2; i++)
            readIn2 >> array2[i];

            for (int i = 0; i < SIZE_2; i++)
            std::cout << array2[i];
        }
        catch(ArrayException e) {std::cout << e;}
*/
return 0;
}
void simpleSort(Array<Integer> &array, int size){
 for (int m = 0; m < size-1; m ++)
    {
        if (array[m] > array[size-1])
            std::swap(array[size-1], array[m]);
    }
    if (size != 0)
        simpleSort(array, size-1);
}

bool linearSearch(int val, Array<Integer> &array){
    for (int i=0; i < array.getSize(); i++){
        if(array[i] == val)
            return true;
        else    return false;
    }
}

bool binarySearch(int val, Integer begin, Integer end, Array<Integer> &array){
    if (begin > end)        return false;
    Integer temp = (begin + end)/2;
    int mid = temp.getVal();
    if (array[mid] == val)
        return true;
    else if (val > array[mid])
        return binarySearch(val, mid++, end, array);
    else
        return binarySearch(val, begin, mid--, array);
}
