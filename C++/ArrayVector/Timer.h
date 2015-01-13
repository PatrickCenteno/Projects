#ifndef TIMER_H
#define TIMER_H

#include <ctime>
#include <iomanip>
#include <sstream>

class Timer{
		//Insertion Operator
        friend std::ostream &operator <<(std::ostream &os, const Timer &timer)
        {
            std::string totalTime;
            std::ostringstream ost;

            ost << std::setprecision(2) << timer._hours << ":" << timer._minutes << ":" << timer._seconds << ":" << timer._ticks;

            totalTime = ost.str();

            os << totalTime << std::endl;
        return os;
        }


    private:
        int _ticks;
        int _seconds;
        int _minutes;
        int _hours;
		clock_t starttime;
		clock_t endtime;
        void setUp();

        //Boolean values for start and stop functions
        bool timerStarted;

    public:
        Timer();
		void start();
		void stop();

		int ticks();
        int seconds();
		int minutes();
		int hours();
};

#endif
