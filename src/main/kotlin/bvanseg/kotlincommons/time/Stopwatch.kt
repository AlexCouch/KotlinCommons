package bvanseg.kotlincommons.time

import bvanseg.kotlincommons.collections.iterables.sumByLong
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

/**
 * A useful utility class used to measure the time between execution of code.
 *
 * @author Boston Vanseghi
 * @since 2.1.6
 */
class Stopwatch {

    private var startTime: Long = 0
    private var stopTime: Long = 0
    private var status: Status = Status.PAUSED

    private val intervals: MutableList<Pair<Long, Long>> = mutableListOf()

    fun start() {
        if(status == Status.STOPPED)
            throw RuntimeException("Can't start a Stopwatch that has already been stopped!")
        else if(status == Status.RUNNING)
            throw RuntimeException("Can't start a Stopwatch that is already running!")
        startTime = System.nanoTime()
        status = Status.RUNNING
    }

    fun pause() {
        if(status == Status.STOPPED)
            throw RuntimeException("Can't pause a Stopwatch that has already been stopped!")
        else if(status == Status.PAUSED)
            throw RuntimeException("Can't pause a Stopwatch that has already been paused!")

        this.stopTime = System.nanoTime()
        status = Status.PAUSED
    }

    fun resume() {
        if(status != Status.PAUSED)
            throw RuntimeException("Can't resume a Stopwatch that isn't paused!")

        startTime += System.nanoTime() - stopTime
        status = Status.RUNNING
    }

    fun newInteval() {
        if(status == Status.STOPPED)
            throw RuntimeException("Can't create a new interval of a Stopwatch that has already been stopped!")
        if(status == Status.PAUSED)
            throw RuntimeException("Can't create a new interval of a Stopwatch that is paused!")

        stopTime = System.nanoTime()
        intervals.add(startTime to stopTime)
        startTime = System.nanoTime()
    }

    fun stop() {
        if(status == Status.STOPPED)
            throw RuntimeException("Can't stop a Stopwatch that has already been stopped!")

        stopTime = System.nanoTime()
        status = Status.STOPPED
        intervals.add(startTime to stopTime)
    }

    fun getElapsedTime(unit: TimeUnit = TimeUnit.NANOSECONDS): Long {
        if(status != Status.STOPPED)
            throw RuntimeException("Can't get time from Stopwatch when it has not been stopped!")

        return if(unit != TimeUnit.NANOSECONDS)
            unit.convert(intervals.sumByLong { it.second - it.first }, TimeUnit.NANOSECONDS)
        else
            intervals.sumByLong { it.second - it.first }
    }

    private enum class Status {
        RUNNING,
        STOPPED,
        PAUSED
    }
}