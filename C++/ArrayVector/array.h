#ifndef ARRAY_H
#define ARRAY_H

#include <iostream>
#include <string>

#include "ArrayException.h"

template <class t>
class Array {
		//Overloaded output operator
        friend std::ostream &operator << (std::ostream &os, const Array<t> &a)
        {
            os << "{";
                for (int i = 0; i < a.size; i++)
                    os << a.memberArray[i] << ", ";
            os << "}" << std::endl;
        return os;
        }

	private:
		t * memberArray;
		int size;
        int capacity;
		void checkCapacity();

	public:
        Array(int s);
        Array(const Array<t> &obj);
        ~Array();
        Array &operator +=(const Array<t> val);
        t &operator [](int index);
        int getSize();

};

    //Constructor
    template <class t>
    Array<t>::Array(int s): size(s)
    {
        capacity = s*2;
        checkCapacity();
        memberArray = new t[capacity];
    }

    //Copy Constructor
    template <class t>
    Array<t>::Array(const Array<t> &obj)
    {
        size = obj.size;
        capacity = obj.capacity;
        memberArray = new t[capacity];
        for (int i = 0; i < size; i++)
            memberArray[i] = obj.memberArray[i];
    }

    //Destructor
    template <class t>
    Array<t>::~Array()    {delete []memberArray;}

    template <class t>
    t &Array<t>::operator [](int index)
    {
        checkCapacity();
        if (index < 0 || index >= size) throw ArrayException("Index out of bounds");
        return memberArray[index];
    }

    template <class t>
    Array<t> &Array<t>::operator +=(const Array<t> val)
    {
        checkCapacity();
        memberArray[size] = val;
        size++;
        return *this;
    }

    template <class t>
    int Array<t>::getSize () {return size;}


    template <class t>
    void Array<t>::checkCapacity() {
        if (size < capacity) return;

        else{
            capacity *= 2;
            t *newArr = new t[capacity];
            for (int i = 0; i < size; i++)
                newArr[i] = memberArray[i];
            memberArray = newArr;
            delete [] memberArray;
        }
    }

#endif
