package com.kotlin.demo.service

import com.kotlin.demo.model.StatsDataModel
import lombok.extern.slf4j.Slf4j
import org.apache.commons.csv.CSVFormat
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Slf4j
class CsvFetchService(var csvUrlFetcher: CSVUrlFetcher) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun fetchAndParseCsv(url: String): List<StatsDataModel> {

        val reader = csvUrlFetcher.fetchCsvFile(url)
        val csvParser = CSVFormat.DEFAULT.builder().setDelimiter(";").build().withHeader().parse(reader)

        val csvContent = mutableListOf<StatsDataModel>()

        for (csvRecord in csvParser) {
            try {
                val row = csvRecord.values()
                val dateString = row[2].trim();
                var recordDate: LocalDate? = null;
                if (dateString.isNotEmpty()) {
                    recordDate = LocalDate.parse(dateString)
                }


                val item = StatsDataModel(
                    row[0].trim(),
                    row[1].trim(),
                    recordDate,
                    row[3].trim().toInt()
                )

                csvContent.addLast(item)

            } catch (e: Exception) {
                logger.error("Error while parsing row {}", e.message)
            }

        }

        return csvContent
    }

}