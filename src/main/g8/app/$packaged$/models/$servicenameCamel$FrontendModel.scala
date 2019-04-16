package $package$.models

import play.api.libs.json.Json

case class $servicenameCamel$FrontendModel(
  parameter1: String,
  parameter2: Option[String],
  telephoneNumber: Option[String],
  emailAddress: Option[String])

object $servicenameCamel$FrontendModel {
  implicit val modelFormat = Json.format[$servicenameCamel$FrontendModel]
}
