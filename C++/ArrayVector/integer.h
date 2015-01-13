#ifndef INTEGER_H
#define INTEGER_H

#include <iostream>

class Integer {
	friend std::ostream &operator <<(std::ostream &os, const Integer &integer);
	friend std::istream &operator >>(std::istream &os, Integer &integer);
    friend Integer operator +(const Integer &i1, const Integer &i2);
    friend Integer operator -(const Integer &i1, const Integer &i2);
    friend Integer operator /(const Integer &i1, const Integer &i2);

public:
	Integer(int val=0) : val(val) {}
	int getVal()    {return val;}
	Integer &operator +=(const Integer &integer);
	Integer &operator -=(const Integer &integer);
	Integer operator ++();
	Integer operator ++(int);
	Integer operator --();
	Integer operator --(int);
	Integer operator =(const Integer &integer);

	bool operator ==(const Integer &right)const
		{	return val == right.val;	}
	friend bool operator >(const Integer &i1, const Integer &i2)
		{	return i1.val > i2.val;	}
	friend bool operator <(const Integer &i1, const Integer &i2)
		{	return i1.val < i2.val;	}
	bool operator !=(const Integer &right)
		{	return !(*this == right.val);	}
private:
	int val;
};

#endif
