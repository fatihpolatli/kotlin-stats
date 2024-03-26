package com.kotlin.demo.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvFetchServiceTest {

    val csvUrlFetcher: CSVUrlFetcher = mockk()

    val csvFetchService: CsvFetchService = CsvFetchService(csvUrlFetcher);


    private val csvContent: String = "Speaker;Topic;Date;Words\n" +
            "Alexander Abel; education policty; 2013-10-30;5310\n" +
            "Bernhard Belling; coal subsidies; 2012-11-05;1210\n" +
            "Caesare Collins; homeland security; 2012-11-06;1119\n" +
            "Alexander Abel; homeland security; 2012-12-11;911"


    @Test
    fun fetchAndParseCsv() {

        val inputStreamReader = InputStreamReader(csvContent.byteInputStream())
        val reader = BufferedReader(inputStreamReader);

        every { csvUrlFetcher.fetchCsvFile("test-url") } returns reader

        val result = csvFetchService.fetchAndParseCsv("test-url")

        assertEquals(result.size, 4)
        assertEquals(result.get(0).speaker,"Alexander Abel")

    }
}