package $package$.connectors

import $package$.wiring.AppConfig

case class TestAppConfig(wireMockBaseUrl: String, wireMockPort: Int) extends AppConfig {

  override val appName: String = "agents-external-stubs"
  override val someInt: Int = wireMockPort
  override val someString: String = wireMockBaseUrl
  override val someBoolean: Boolean = false
  override val authBaseUrl: String = wireMockBaseUrl
  override val serviceBaseUrl: String = wireMockBaseUrl
}
