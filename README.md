# Application to process data from online CSV files with Kotlin

### Description

This application process csv data about specific statistics and return desired results. There are 2 services getting CSV file url. Services fetchs CSV file, process data inside and return desired statistics in desired format.

#### Compile instructions

Application is developed using Kotlin with Spring Boot 3.x and Maven. MockK library is used for Unit testing.

It can be compiled with maven command

```
mvn clean install
```

#### Swagger UI can be accessed from the url

```
/swagger-ui/index.html
```

#### Sample CSV file format

```
Speaker;Topic;Date;Words
Alexander Abel;education policty;2013-10-30;5310
Bernhard Belling;coal subsidies;2012-11-05;1210
Caesare Collins;homeland security;2012-11-06;1119
Alexander Abel;homeland security;2012-12-11;911
```

#### Data example returned from the service getting single url parameter

```
    /stats/csv?url=http://sample.url/test.csv
```

```
{
    "mostSpeeches": "Alexander Abel",
    "mostSecurity": "Caesare Collins",
    "leastWordy": "Alexander Abel"
}
```

#### Data example returned from the service getting multiple url parameters

```
    /stats/csv-multiple?url1=http://sample.url1/test.csv&?url2=http://sample.url2/test.csv2&?url3=http://sample.url3/test.csv
```

```
[
    {
        "url": "http://sample.url1/test.csv",
        "data": {
            "mostSpeeches": "Alexander Abel",
            "mostSecurity": "Caesare Collins",
            "leastWordy": "Alexander Abel"
        }
    },
    {
        "url": "http://sample.url2/test.csv",
        "data": {
            "mostSpeeches": "Alexander Abel",
            "mostSecurity": "Caesare Collins",
            "leastWordy": "Alexander Abel"
        }
    },
    {
        "url": "http://sample.url3/test.csv",
        "data": {
            "mostSpeeches": "Alexander Abel",
            "mostSecurity": "Caesare Collins",
            "leastWordy": "Alexander Abel"
        }
    }
]
```
