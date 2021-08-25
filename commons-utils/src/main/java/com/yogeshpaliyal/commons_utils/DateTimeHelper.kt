@file:JvmName(name = "DateTimeHelper")

package com.yogeshpaliyal.commons_utils

import android.text.format.DateUtils
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 26-12-2020 20:33
*/


fun getCurrentCalendarMilliseconds(
    clearDate: Boolean = false,
    clearTime: Boolean = false,
    clearSecond: Boolean = false,
    clearMillisecond: Boolean = false
): Long {
    return getCurrentCalendar(
        clearDate,
        clearTime,
        clearSecond,
        clearMillisecond
    ).timeInMillis
}

fun getMonth(month: Int): String? {
    return DateFormatSymbols().months[month - 1]
}

fun Calendar?.getMonthName(): String? {
    return this?.let {
        DateFormatSymbols().months[it.get(Calendar.MONTH)]
    } ?: ""
}


fun String?.convertServerToLocal() = this.convertDateFormat(DATE_FORMAT.SERVER_DATE_FORMAT, DATE_FORMAT.LOCAL_DATE_FORMAT ) ?: ""
fun String?.convertLocalToServer() = this.convertDateFormat(DATE_FORMAT.LOCAL_DATE_FORMAT, DATE_FORMAT.SERVER_DATE_FORMAT ) ?: ""

fun Long?.formatToLocalTime() = this?.formatCalendar(TIME_FORMAT.LOCAL_TIME_FORMAT ) ?: ""
fun Long?.formatToLocalDate() = this?.formatCalendar( DATE_FORMAT.LOCAL_DATE_FORMAT ) ?: ""

fun Long?.formatToLocalDateTime() = this?.formatCalendar(DATE_FORMAT.LOCAL_DATE_TIME_FORMAT ) ?: ""


fun Long?.formatToServerTime() = this?.formatCalendar(TIME_FORMAT.SERVER_TIME_FORMAT ) ?: ""
fun Long?.formatToServerDate() = this?.formatCalendar(DATE_FORMAT.SERVER_DATE_FORMAT ) ?: ""


fun Calendar?.getAge(): Int {
    if (this == null)
        return 0

    val today = Calendar.getInstance()

    // dob.set(year, month, day);
    var age = today[Calendar.YEAR] - this[Calendar.YEAR]
    /*if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
        age--;
    }*/if (today[Calendar.MONTH] < this[Calendar.MONTH]) {
        age--
    } else if (today[Calendar.MONTH] == this[Calendar.MONTH]) {
        val day1 = today[Calendar.DAY_OF_MONTH]
        val day2 = this[Calendar.DAY_OF_MONTH]
        if (day2 > day1) {
            age--
        }
    }
    // String ageS = ageInt.toString();
    return age
}

fun String?.convertDateFormat(
    currentFormat: String?,
    newFormat: String?
): String? {
    var date = this
    try {
        var spf = SimpleDateFormat(currentFormat)
        val newDate = spf.parse(date)
        spf = SimpleDateFormat(newFormat)
        date = spf.format(newDate)
        return date
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

fun getCurrentCalendar(
    clearDate: Boolean = false,
    clearTime: Boolean = false,
    clearSecond: Boolean = false,
    clearMillisecond: Boolean = false
): Calendar {
    val calendar = Calendar.getInstance()
    calendar.isLenient = false
    calendar.firstDayOfWeek = Calendar.SUNDAY
    if (clearDate) {
        calendar[Calendar.DATE] = 1
        calendar[Calendar.MONTH] = 1
        calendar[Calendar.YEAR] = 1970
    }
    if (clearTime) {
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }
    if (clearSecond) {
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }
    if (clearMillisecond) calendar[Calendar.MILLISECOND] = 0
    return calendar
}

fun Long?.getCalendar( clearDate: Boolean = false, clearTime: Boolean = false,
    clearSecond: Boolean = false, clearMillisecond: Boolean = false
): Calendar {
    val calendar = Calendar.getInstance()
    calendar.isLenient = false
    calendar.firstDayOfWeek = Calendar.SUNDAY
    calendar.timeInMillis = this ?: 0
    if (clearDate) {
        calendar[Calendar.DATE] = 1
        calendar[Calendar.MONTH] = 1
        calendar[Calendar.YEAR] = 1970
    }
    if (clearTime) {
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }
    if (clearSecond) {
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }
    if (clearMillisecond) calendar[Calendar.MILLISECOND] = 0
    return calendar
}

val currentTimeStamp: Long
    get() = try {
        System.currentTimeMillis() / 1000
    } catch (e: Exception) {
        e.printStackTrace()
        0L
    }

val currentTimeStampInMillis: Long
    get() = try {
        System.currentTimeMillis()
    } catch (e: Exception) {
        e.printStackTrace()
        0L
    }

fun Calendar?.differenceBetweenCalendarInDays(
    toCalendar: Calendar
): Long {
    return (toCalendar.timeInMillis - (this?.timeInMillis?:0)) / TimeUnit.DAYS.toMillis(
        1
    )
}

/**
 * @return true if the supplied when is today else false
 */
fun Calendar?.isTodayCalendar(): Boolean {
    if (this == null)
        return false
    val todayCalendar = Calendar.getInstance()
    return (todayCalendar[Calendar.YEAR] == this[Calendar.YEAR]
            && todayCalendar[Calendar.MONTH] == this[Calendar.MONTH]
            && todayCalendar[Calendar.DATE] == this[Calendar.DATE])
}

fun Long?.formatIntoString(): String {
    var str = ""
    val days = TimeUnit.MILLISECONDS.toDays(this ?: 0).toInt()
    val hours =
        TimeUnit.MILLISECONDS.toHours(this?:0) -
                TimeUnit.DAYS.toHours(days.toLong())
    val minutes =
        TimeUnit.MILLISECONDS.toMinutes(this?:0) -
                TimeUnit.DAYS.toMinutes(days.toLong()) -
                TimeUnit.HOURS.toMinutes(hours)
    val seconds =
        TimeUnit.MILLISECONDS.toSeconds(this?:0) -
                TimeUnit.DAYS.toSeconds(days.toLong()) -
                TimeUnit.HOURS.toSeconds(hours) -
                TimeUnit.MINUTES.toSeconds(minutes)
    if (days != 0) str += days.toString() + " day" + if (days > 1) "s " else " "
    if (hours != 0L) str += hours.toString() + " hour" + if (hours > 1) "s " else " "
    if (minutes != 0L) str += minutes.toString() + " minute" + if (minutes > 1) "s " else " "
    if (seconds != 0L) str += seconds.toString() + " second" + if (seconds > 1) "s" else ""
    return str
}

fun Long?.formatIntoStringFull(): String {
    var str = ""
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this ?: 0
    val years = calendar[Calendar.YEAR] - 1970
    val months = calendar[Calendar.MONTH]
    val weeks = calendar[Calendar.DAY_OF_MONTH] / 7
    val days = calendar[Calendar.DAY_OF_MONTH] - 1 - weeks * 7
    val hours = calendar[Calendar.HOUR_OF_DAY].toLong()
    val minutes = calendar[Calendar.MINUTE].toLong()
    val seconds = calendar[Calendar.SECOND].toLong()
    if (years != 0) str += years.toString() + " year" + if (years > 1) "s " else " "
    if (months != 0) str += months.toString() + " month" + if (months > 1) "s " else " "
    if (weeks != 0) str += weeks.toString() + " week" + if (weeks > 1) "s " else " "
    if (days != 0) str += days.toString() + " day" + if (days > 1) "s " else " "
    if (hours != 0L) str += hours.toString() + " hour" + if (hours > 1) "s " else " "
    if (minutes != 0L) str += minutes.toString() + " minute" + if (minutes > 1) "s " else " "
    if (seconds != 0L) str += seconds.toString() + " second" + if (seconds > 1) "s" else ""
    return str
}

fun Long?.formatIntoShortString(): String {
    val str = ""
    val days = TimeUnit.MILLISECONDS.toDays(this?:0).toInt()
    if (days > 0) return days.toString() + ":" + if (days > 1) "s" else ""
    val hours =
        TimeUnit.MILLISECONDS.toHours(this?:0) -
                TimeUnit.DAYS.toHours(days.toLong())
    if (hours > 0) return hours.toString() + " hr" + if (hours > 1) "s" else ""
    val minutes =
        TimeUnit.MILLISECONDS.toMinutes(this?:0) -
                TimeUnit.DAYS.toMinutes(days.toLong()) -
                TimeUnit.HOURS.toMinutes(hours)
    if (minutes > 0) return minutes.toString() + " min" + if (minutes > 1) "s" else ""
    val seconds =
        TimeUnit.MILLISECONDS.toSeconds(this?:0) -
                TimeUnit.DAYS.toSeconds(days.toLong()) -
                TimeUnit.HOURS.toSeconds(hours) -
                TimeUnit.MINUTES.toSeconds(minutes)
    return if (seconds > 0) seconds.toString() + " sec" + (if (seconds > 1) "s" else "") else str
}

fun Long?.formatIntoRestTimeString(): String {
    val str = ""
    val days = TimeUnit.MILLISECONDS.toDays(this?:0).toInt()
    if (days > 0) return "$days:"
    val hours =
        TimeUnit.MILLISECONDS.toHours(this?:0) -
                TimeUnit.DAYS.toHours(days.toLong())
    if (hours > 0) return hours.toString() + ":"
    val minutes =
        TimeUnit.MILLISECONDS.toMinutes(this?:0) -
                TimeUnit.DAYS.toMinutes(days.toLong()) -
                TimeUnit.HOURS.toMinutes(hours)
    if (minutes > 0) return minutes.toString() + ":"
    val seconds =
        TimeUnit.MILLISECONDS.toSeconds(this?:0) -
                TimeUnit.DAYS.toSeconds(days.toLong()) -
                TimeUnit.HOURS.toSeconds(hours) -
                TimeUnit.MINUTES.toSeconds(minutes)
    return if (seconds > 0) seconds.toString() + ":" else ""
}

fun Calendar?.formatCalendar(
    dateTimeFormat: String?
): String {
    val simpleDateFormat =
        SimpleDateFormat(dateTimeFormat, Locale.getDefault())
    return simpleDateFormat.format(this?.time)
}

fun Long?.formatCalendar(dateTimeFormat: String?): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this?:0
    val simpleDateFormat =
        SimpleDateFormat(dateTimeFormat, Locale.getDefault())
    return simpleDateFormat.format(calendar.time)
}

fun Long?.formatCalendarFromSec(dateTimeFormat: String?): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = (this?:0)*1000
    val simpleDateFormat =
        SimpleDateFormat(dateTimeFormat, Locale.getDefault())
    return simpleDateFormat.format(calendar.time)
}

fun String?.getCurrentDateTime(
    timeZone: TimeZone?
): String {
    return try {
        val caledarObj = Calendar.getInstance(timeZone)
        val dateFormatter =
            SimpleDateFormat(this, Locale.getDefault())
        dateFormatter.timeZone = timeZone
        val dateStr = dateFormatter.format(caledarObj.time)
        caledarObj.clear()
        dateStr
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Long?.convertUnixTimeStampIntoRelativeDate(): String {
    return if (this != null && this > 0) {
        val currentTimeStamp = currentTimeStampInMillis
        val diff = currentTimeStamp - this
        if (diff < 60) "Just Now" else {
            val relativeTimeSpan = DateUtils.getRelativeTimeSpanString(
                this,
                currentTimeStamp,
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL
            )
            relativeTimeSpan.toString()
        }
    } else {
        "Just Now"
    }
}

fun Long?.convertUnixTimeStampIntoDateStr(
    dateFormatStr: String?,
    timeZone: TimeZone?
): String {
    return if (this == 0L) "" else try {
        val date = Date(this?:0)
        val dateFormatter =
            SimpleDateFormat(dateFormatStr, Locale.getDefault())
        dateFormatter.timeZone = timeZone
        dateFormatter.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun convertDateStrIntoUnixTimeStamp(
    dateTimeStr: String?,
    dateFormatStr: String?,
    timeZone: TimeZone?
): Long {
    return try {
        val dateFormatter =
            SimpleDateFormat(dateFormatStr, Locale.getDefault())
        dateFormatter.timeZone = timeZone
        val date = dateFormatter.parse(dateTimeStr)
        date.time
    } catch (e: ParseException) {
        e.printStackTrace()
        0
    }
}

fun convertDateStrIntoCalendar(
    dateTimeStr: String?,
    dateFormatStr: String?
): Calendar? {
    return try {
        val cal = Calendar.getInstance()
        val sdf =
            SimpleDateFormat(dateFormatStr, Locale.getDefault())
        cal.time = sdf.parse(dateTimeStr)
        cal
    } catch (e: Exception) {
        null
    }
}

fun convertDateStrIntoCalendar2(
    dateTimeStr: String?,
    dateFormatStr: String?
): Calendar? {
    return try {
        val cal = Calendar.getInstance()
        val cal1 = Calendar.getInstance()
        val sdf =
            SimpleDateFormat(dateFormatStr, Locale.getDefault())
        cal.time = sdf.parse(dateTimeStr)
        cal1[Calendar.HOUR_OF_DAY] = cal[Calendar.HOUR_OF_DAY]
        cal1[Calendar.MINUTE] = cal[Calendar.MINUTE]
        cal
    } catch (e: Exception) {
        null
    }
}

fun Long?.convertTimeStampIntoCalendar(): Calendar? {
    return try {
        val cal = Calendar.getInstance()
        cal.timeInMillis = this ?: 0
        cal
    } catch (e: Exception) {
        null
    }
}

fun getSumOfDayInMonth(year: Int, month: Int): Int {
    return GregorianCalendar(year, month, 0)[Calendar.DATE]
}

fun TimeZone?.getTimeZoneString(): String {
    return this?.id ?: ""
}