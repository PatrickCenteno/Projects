#include <iostream>
#include <iomanip>
#include <sstream>
#include <ctime>
#include <string>

#include "Timer.h"
#include "TimerException.h"

//Constructor to initialize all values to zero
Timer::Timer() :
	_ticks(0),
	_seconds(0),
	_minutes(0),
	_hours(0),
	timerStarted(false)	{}

void Timer::start()
{
    if (timerStarted)
        throw TimerException("Timer already started");
    else
        starttime = clock();
        timerStarted = true;
}

void Timer::stop()
{
    if (!timerStarted)
        throw TimerException("Timer has not started");
    else{
        endtime = clock();

        _ticks = (endtime - starttime) % CLOCKS_PER_SEC;
        _seconds = (endtime - starttime) / CLOCKS_PER_SEC;
        _minutes = _seconds / 60;
        _seconds %= 60;
        _hours = _minutes / 60;
        _minutes %= 60;
        timerStarted = false;
    }
}

int Timer::ticks()
{
    if(!timerStarted)
        return _ticks;
    else
        throw TimerException("Timer still running");
}

int Timer::seconds()
{
    if(!timerStarted)
        return _seconds;
    else
        throw TimerException("Timer still running");
}

int Timer::minutes()
{
    if(!timerStarted)
        return _minutes;
    else
        throw TimerException("Timer still running");
}

int Timer::hours()
{
    if(!timerStarted)
        return _hours;
    else
        throw TimerException("Timer still running");
}



