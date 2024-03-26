package com.kotlin.demo.controller

import com.kotlin.demo.service.StatsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("stats")
class StatisticsController(var statsService: StatsService) {

    @GetMapping("csv")
    fun fetchCsvResult(@RequestParam("url") url: String) = statsService.processResults(url)

    @GetMapping("csv-multiple")
    fun fetchCsvFilesResults(@RequestParam params: Map<String, String>) =
        statsService.processMultiple(params.values.toList())
}
