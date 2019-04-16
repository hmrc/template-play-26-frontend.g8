package $package$.wiring

import com.google.inject.ImplementedBy
import javax.inject.Inject
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig

@ImplementedBy(classOf[AppConfigImpl])
trait AppConfig {

  val appName: String
  val someInt: Int
  val someString: String
  val someBoolean: Boolean
  val authBaseUrl: String
  val serviceBaseUrl: String
}

class AppConfigImpl @Inject()(config: ServicesConfig) extends AppConfig {
  val appName = config.getString("appName")

  val someInt = config.getInt("$servicenameHyphen$.someInt")
  val someString = config.getString("$servicenameHyphen$.someString")
  val someBoolean = config.getBoolean("$servicenameHyphen$.someBoolean")
  val authBaseUrl = config.baseUrl("auth")
  val serviceBaseUrl = config.baseUrl("$servicenameHyphen$")
}
