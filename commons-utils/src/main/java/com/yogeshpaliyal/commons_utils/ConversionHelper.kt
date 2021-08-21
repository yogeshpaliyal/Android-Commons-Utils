@file:JvmName("ConversionHelper")
package com.yogeshpaliyal.commons_utils


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 26-12-2020 19:49
*/

fun Any?.safeToLong(): Long {
    return try {
        this?.toString()?.toLong() ?: 0
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        0
    }
}

fun Any?.safeToString(): String {
    return try {
        this?.toString() ?: ""
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ""
    }
}

fun String?.convertToDouble(): Double {
    return if (this == null || this.trim { it <= ' ' } == "") 0.00 else try {
        this.toDouble()
    } catch (e: Exception) {
        e.printStackTrace()
        0.00
    }
}


