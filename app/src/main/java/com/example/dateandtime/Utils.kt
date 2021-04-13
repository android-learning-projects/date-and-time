package com.example.dateandtime

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun toCalendar(date: Date): Calendar? {
        return try {
            val cal: Calendar = Calendar.getInstance()
            cal.time = date
            cal
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    fun toDate(date: String, format: String):Date?{
        return try {
            val sdf =
                SimpleDateFormat(format) // here set the pattern as you date in string was containing like date/month/year
            val d: Date = sdf.parse(date)
            d
        } catch (ex: ParseException) {
            null
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }
    }
}