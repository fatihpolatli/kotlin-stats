package com.kotlin.demo.service

import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI
import java.nio.charset.StandardCharsets

@Component
class CSVUrlFetcher {

    fun fetchCsvFile(url: String): BufferedReader {
        val csvUrl = URI.create(url).toURL()

        val inputStreamReader = InputStreamReader(csvUrl.openStream(), StandardCharsets.UTF_8)
        return BufferedReader(inputStreamReader)
    }
}