package com.kotlin.demo.service

import com.kotlin.demo.model.StatsDataModel
import com.kotlin.demo.model.StatsResultModel
import com.kotlin.demo.model.UrlResultModel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StatsService(var csvFetchService: CsvFetchService) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun processResults(url: String): StatsResultModel {

        val csvData = csvFetchService.fetchAndParseCsv(url)

        val mostSpeech = getMostSpeech(csvData)
        val mostSecurity = getMostSecurity(csvData)
        val fewestWord = getFewestWord(csvData)

        return StatsResultModel(mostSpeech.speaker, mostSecurity.speaker, fewestWord.speaker)

    }

    fun processMultiple(urls: List<String>): List<UrlResultModel> =
        urls.stream().map { UrlResultModel(it, processResults(it)) }.toList()

    private fun getMostSpeech(csvData: List<StatsDataModel>): StatsDataModel =
        csvData.stream().filter { it.date?.year == 2013 }.toList().maxBy { p -> p.wordsCount }

    private fun getMostSecurity(csvData: List<StatsDataModel>): StatsDataModel =
        csvData.stream().filter { it.topic == "homeland security" }.toList().maxBy { p -> p.wordsCount }

    private fun getFewestWord(csvData: List<StatsDataModel>): StatsDataModel = csvData.minBy { p -> p.wordsCount }

}