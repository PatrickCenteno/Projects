#ifndef TIMEREXCEPTION_H
#define TIMEREXCEPTION_H

#include "Timer.h"

#include <string>

class TimerException {
    friend std::ostream &operator <<(std::ostream &os, const TimerException &e) {
		os << "*** TimerException *** " << e.message;
		return os;
	}
	private:
        const char *message;

    public:
        TimerException (const char *m) : message(m) {}
};

#endif

