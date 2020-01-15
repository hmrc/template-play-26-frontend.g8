A [Giter8](http://www.foundweekends.org/giter8/) template for creating HMRC Digital Scala Play 2.6 Frontend

How to create a new project based on the template?
==

* Install g8 commandline tool (http://www.foundweekends.org/giter8/setup.html)
* Go to the directory where you want to create the template
* Decide your service name (the hardest part :))
* Run the command

    `g8 hmrc/template-play-26-frontend.g8 --servicename="New Shiny Service 26" --serviceTargetPort="9999" --package="uk.gov.hmrc.newshinyservice26frontend"`
    
and then
    
    cd new-shiny-service-26-frontend
    git init
	git add .
	git commit -m start
  
* Test generated project using command 

    `sbt test it:test`
    

How to test the template and generate an example project?
==

* Run `./test.sh` 

An example project will be then created and tested in `target/sandbox/new-shiny-service-26-frontend`

How to modify the template?
==

 * review template sources in `/src/main/g8`
 * modify files as you need, but be careful about placeholders, paths and so on
 * run `./test.sh` in template root to validate your changes
 
or (safer) ...

* run `./test.sh` first
* open `target/sandbox/new-shiny-service-26-frontend` in your preferred IDE, 
* modify the generated example project as you wish, 
* build and test it as usual, you can run `sbt test it:test`,
* when you are done switch back to the template root
* run `./update-g8.sh` in order to port your changes back to the template.
* run `./test.sh` again to validate your changes

What is in the template?
==

Assuming the command above 
the template will supply the following values for the placeholders:

    $packaged$ -> uk/gov/hmrc/newshinyservice26frontend
	$package$ -> uk.gov.hmrc.newshinyservice26frontend
	$servicenameCamel$ -> NewShinyService26
	$servicenamecamel$ -> newShinyService26
	$servicenameSnake$ -> NEW_SHINY_SERVICE_26
	$servicenamePackage$ -> New.Shiny.Service.26
	$servicenamePackageLowercase$ -> new.shiny.service.26
	$servicenamePackaged$ -> New/Shiny/Service/26
	$servicenamePackagedLowercase$ -> new/shiny/service/26
	$servicenameHyphen$ -> new-shiny-service-26
	$servicenameLowercase$ -> new shiny service 26
	$servicenameUppercase$ -> NEW SHINY SERVICE 26
	$servicename$ -> New Shiny Service 26
	$serviceTargetPort$ -> 9999

and produce the folders and files as shown below:

    ├── .gitignore
	├── .scalafmt.conf
	├── app
	│   ├── ErrorHandler.scala
	│   ├── FrontendModule.scala
	│   └── uk
	│       └── gov
	│           └── hmrc
	│               └── newshinyservice26frontend
	│                   ├── connectors
	│                   │   ├── FrontendAuthConnector.scala
	│                   │   └── NewShinyService26Connector.scala
	│                   │
	│                   ├── controllers
	│                   │   ├── AuthActions.scala
	│                   │   ├── NewShinyService26FrontendController.scala
	│                   │   └── package.scala
	│                   │
	│                   ├── models
	│                   │   └── NewShinyService26FrontendModel.scala
	│                   │
	│                   ├── services
	│                   │   └── AuditService.scala
	│                   │
	│                   ├── views
	│                   │   ├── error_prefix.scala.html
	│                   │   ├── error_template.scala.html
	│                   │   ├── govuk_wrapper.scala.html
	│                   │   ├── main_template.scala.html
	│                   │   ├── newShinyService26FrontendForm.scala.html
	│                   │   ├── start.scala.html
	│                   │   └── summary.scala.html
	│                   │
	│                   └── wiring
	│                       └── AppConfig.scala
	│
	├── build.sbt
	├── conf
	│   ├── app.routes
	│   ├── application-json-logger.xml
	│   ├── application.conf
	│   ├── logback.xml
	│   ├── messages
	│   └── prod.routes
	│
	├── it
	│   └── uk
	│       └── gov
	│           └── hmrc
	│               └── newshinyservice26frontend
	│                   ├── connectors
	│                   │   ├── NewShinyService26ConnectorISpec.scala
	│                   │   └── TestAppConfig.scala
	│                   │
	│                   ├── controllers
	│                   │   ├── AuthActionsISpec.scala
	│                   │   └── NewShinyService26FrontendControllerISpec.scala
	│                   │
	│                   ├── stubs
	│                   │   ├── AuthStubs.scala
	│                   │   └── DataStreamStubs.scala
	│                   │
	│                   └── support
	│                       ├── BaseISpec.scala
	│                       ├── MetricsTestSupport.scala
	│                       └── WireMockSupport.scala
	│
	├── LICENSE
	├── project
	│   ├── build.properties
	│   └── plugins.sbt
	│
	├── README.md
	├── repository.yaml
	└── test
	    └── uk
	        └── gov
	            └── hmrc
	                └── newshinyservice26frontend
	                    ├── controllers
	                    │   └── NewShinyService26FrontendFormSpec.scala
	                    │
	                    ├── services
	                    │   └── AuditServiceSpec.scala
	                    │
	                    └── views
	                        └── ViewsSpec.scala