package com.kotlin.demo.model


import java.time.LocalDate

data class StatsDataModel(val speaker:String, val topic:String, val date:LocalDate?, val wordsCount:Int)