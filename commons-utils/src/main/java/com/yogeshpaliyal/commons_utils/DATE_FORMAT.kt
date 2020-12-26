package com.yogeshpaliyal.commons_utils


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 26-12-2020 20:35
*/

annotation class DATE_FORMAT {
    companion object {
        var DD = "dd"
        var MM = "MM"
        var YYYY = "yyyy"
        var MMM_YYYY = "MMM yyyy"
        var DD_MM_YYYY = "dd/MM/yyyy"
        var DD_MMMM_YYYY = "dd MMMM, yyyy"
        var DD_MMM_COMMA_YYYY = "dd MMM, yyyy"
        var DD_MMM_YYYY = "dd MMM yyyy"
        var DD_MMM = "dd MMM"
        var EEEE = "EEEEE"
        var MMMM_YYYY = "MMMM yyyy"
        var MMMM_DD_YYYY = "MMMM dd, yyyy"
        var EEE_DD_MMM_YYYY = "EEE, dd MMM yyyy"
        var EEE_DD_MMMM_YYYY = "EEE, dd MMMM yyyy"
        var EEE_DD_MMM = "EEE, dd MMM"
        var EEEE_DD_MMM = "EEEE, dd MMM"
        var EEEE_MMMM_DD = "EEEE, MMMM dd"
        var EEE_MMMM_DD_YYYY = "EEE, MMM dd yyyy"
        var YYYY_MM_DD = "yyyy-MM-dd"
        var MM_YY = "MM/YY"
        val SERVER_DATE_FORMAT = "yyyy-MM-dd"
        val LOCAL_DATE_FORMAT = "MMM dd, yyyy"
        val LOCAL_DATE_TIME_FORMAT = "dd MMM yyyy, hh:mm aa"
    }
}