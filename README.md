# UI and API tests example
A simple example of an automation test

## To run tests and generate report from console:
`run mvn clean test`
* start Chrome and access https://yandex.ru (3 successful tests and 1 failed)
* start api and access https://disk.yandex.ru (3 successful tests)
* look in build/reports/tests for the .html and screenshot files

`run mvn clean validate -Pjmeter`
* start Jmeter and access https://yandex.ru (1 successful tests)
* look in target/jmeter/results/ for the JMeter results file