package com.kotlin.demo.service

import com.kotlin.demo.model.StatsDataModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class StatsServiceTest{

    val dataList = listOf(
        StatsDataModel("speaker1","topic1",LocalDate.parse("2011-02-02"),22),
        StatsDataModel("speaker2","topic2",LocalDate.parse("2013-02-02"),25),
        StatsDataModel("speaker3","homeland security",LocalDate.parse("2012-02-02"),32))

    val csvFetchService: CsvFetchService = mockk()

    val statsService = StatsService(csvFetchService)

    @Test
    fun processResults() {

        every { csvFetchService.fetchAndParseCsv("test-url") } returns dataList

        val result = statsService.processResults("test-url")

        assertEquals(result.mostSpeeches,"speaker2")
        assertEquals(result.mostSecurity,"speaker3")
        assertEquals(result.leastWordy,"speaker1")

    }


}