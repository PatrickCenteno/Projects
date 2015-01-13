#include <iostream>

#include "integer.h"

std::ostream &operator <<(std::ostream &os, const Integer &integer)
{
	os << integer.val << std::endl;
	return os;
}

std::istream &operator >>(std::istream &os, Integer &integer)
{
	os >> integer.val;
	return os;
}

Integer operator +(const Integer &i1, const Integer &i2)
{
	Integer result = i1;
	return result += i2;
}

Integer operator -(const Integer &i1, const Integer &i2)
{
    Integer result = i1;
    return result -= i2;
}

Integer operator /(const Integer &i1, const Integer &i2)
{
    Integer result = i1;
    result.val / i2.val;
    return result;
}

Integer &Integer::operator +=(const Integer &integer)
{
	val += integer.val;
	return *this;
}


Integer &Integer::operator -=(const Integer &integer)
{
	val -= integer.val;
	return *this;
}

Integer Integer::operator ++()
{
	++val;
	return *this;
}

Integer Integer::operator ++(int i)
{
	Integer temp (val);
	val++;
	return temp;
}

Integer Integer::operator --()
{
	--val;
	return *this;
}

Integer Integer::operator -- (int i)
{
	Integer temp (val);
	val--;
	return temp;
}

Integer Integer::operator = (const Integer &integer)
{
	val = integer.val;
	return *this;
}


