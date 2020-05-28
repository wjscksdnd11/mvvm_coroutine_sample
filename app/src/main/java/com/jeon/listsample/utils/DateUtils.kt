package com.jeon.listsample.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun getTodayDate():String{
    val format = "EEE, MMM dd, yy"
    return  SimpleDateFormat(format, Locale.getDefault()).format(Date())
}

fun getDate(dt:Int):String{
    return try{
        val dtLong = dt.toLong()*1000
        val format = "MM/dd"
        SimpleDateFormat(format, Locale.getDefault()).format(Date(dtLong))
    }catch (e:Exception){
        ""
    }
}

fun getWeek(dt:Int):String{
    return try{
        val dtLong = dt.toLong()*1000
        val format = "EEE"
        SimpleDateFormat(format, Locale.getDefault()).format(Date(dtLong))
    }catch (e:Exception){
        ""
    }
}