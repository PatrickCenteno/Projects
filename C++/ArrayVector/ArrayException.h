#ifndef ARRAY_EXCEPTION_H
#define ARRAY_EXCEPTION_H

#include <iostream>
#include <string>

class ArrayException {
	friend std::ostream &operator <<(std::ostream &os, const ArrayException &e) {
		os << "*** ArrayException *** " << e.message;
		return os;
	}
public:
	ArrayException(std::string message) : message(message) {}
private:
	 std::string message;
};

#endif