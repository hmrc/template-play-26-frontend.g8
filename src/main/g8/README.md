# $servicename$ Frontend

[ ![Download](https://api.bintray.com/packages/hmrc/releases/$servicenameHyphen$-frontend/images/download.svg) ](https://bintray.com/hmrc/releases/$servicenameHyphen$-frontend/_latestVersion)

## Running the tests

    sbt test it:test

## Running the tests with coverage

    sbt clean coverageOn test it:test coverageReport

## Running the app locally

    sm --start AGENTS_STUBS -f
    sbt run

It should then be listening on port $serviceTargetPort$

    browse http://localhost:$serviceTargetPort$/$servicenameHyphen$

### License


This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html")
