package $package$.connectors

import com.github.tomakehurst.wiremock.client.WireMock._
import play.api.http.Status
import play.api.libs.json.Json
import uk.gov.hmrc.http._
import $package$.models.$servicenameCamel$FrontendModel
import $package$.support.BaseISpec

import scala.concurrent.ExecutionContext.Implicits.global

class $servicenameCamel$ConnectorISpec extends BaseISpec {
  private implicit val hc = HeaderCarrier()

  private lazy val connector: $servicenameCamel$Connector =
    app.injector.instanceOf[$servicenameCamel$Connector]

  private val model = $servicenameCamel$FrontendModel(
    "Dave Agent",
    Some("AA1 1AA"),
    Some("0123456789"),
    Some("email@test.com"))

  "$servicenameCamel$Connector" when {

    "getSmth" should {

      "return 200" in {
        stubFor(
          get(urlEqualTo(s"/$servicenameHyphen$/dosmth"))
            .willReturn(aResponse()
              .withStatus(Status.OK)
              .withBody(Json.obj("foo" -> "bar").toString())))

        val response: HttpResponse = await(connector.getSmth())
        response.status shouldBe 200
        verifyTimerExistsAndBeenUpdated("ConsumedAPI-$servicenameHyphen$-smth-GET")
      }

      "throw an exception if no connection was possible" in {
        stopWireMockServer()
        intercept[BadGatewayException] {
          await(connector.getSmth())
        }
        startWireMockServer()
      }

      "throw an exception if the response is 400" in {
        stubFor(
          get(urlEqualTo(s"/$servicenameHyphen$/dosmth"))
            .willReturn(aResponse()
              .withStatus(Status.BAD_REQUEST)))

        intercept[BadRequestException] {
          await(connector.getSmth())
        }
      }
    }

    "postSmth" should {

      "return 201" in {
        stubFor(
          post(urlEqualTo(s"/$servicenameHyphen$/dosmth"))
            .willReturn(aResponse()
              .withStatus(Status.CREATED)))

        val response: HttpResponse = await(connector.postSmth(model))
        response.status shouldBe 201
        verifyTimerExistsAndBeenUpdated("ConsumedAPI-$servicenameHyphen$-smth-POST")
      }

      "throw an exception if no connection was possible" in {
        stopWireMockServer()
        intercept[BadGatewayException] {
          await(connector.postSmth(model))
        }
        startWireMockServer()
      }

      "throw an exception if the response is 400" in {
        stubFor(
          post(urlEqualTo(s"/$servicenameHyphen$/dosmth"))
            .willReturn(aResponse()
              .withStatus(Status.BAD_REQUEST)))

        intercept[BadRequestException] {
          await(connector.postSmth(model))
        }
      }
    }
  }

}
