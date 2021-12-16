package com.example.closetbuddy.utils

import com.example.closetbuddy.R
import com.example.closetbuddy.models.Season
import java.time.LocalDateTime
import java.time.Month

object CalendarHelper {

    fun getSeason() : Season {
        val current = LocalDateTime.now()
        return when (current.month) {
            Month.JANUARY -> Season.WINTER
            Month.FEBRUARY -> Season.WINTER
            Month.MARCH -> Season.SPRING
            Month.APRIL -> Season.SPRING
            Month.MAY -> Season.SPRING
            Month.JUNE -> Season.SUMMER
            Month.JULY -> Season.SUMMER
            Month.AUGUST -> Season.SUMMER
            Month.SEPTEMBER -> Season.FALL
            Month.NOVEMBER -> Season.FALL
            Month.DECEMBER -> Season.WINTER
            else -> Season.NONE
        }
    }

    fun getSeasonName(season: Season) : Int {
        return when (season) {
            Season.SUMMER -> R.string.footwear
            Season.SPRING -> R.string.bottoms
            Season.FALL -> R.string.tops
            Season.WINTER -> R.string.outerwear
            else -> R.string.bottoms
        }
    }
}