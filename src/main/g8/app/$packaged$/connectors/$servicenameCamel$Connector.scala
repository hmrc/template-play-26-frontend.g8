package $package$.connectors

import java.net.URL

import com.codahale.metrics.MetricRegistry
import com.kenshoo.play.metrics.Metrics
import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.agent.kenshoo.monitoring.HttpAPIMonitor
import uk.gov.hmrc.http.{HeaderCarrier, HttpGet, HttpPost, HttpResponse}
import $package$.models.$servicenameCamel$FrontendModel
import $package$.wiring.AppConfig

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class $servicenameCamel$Connector @Inject()(appConfig: AppConfig, http: HttpGet with HttpPost, metrics: Metrics)
    extends HttpAPIMonitor {

  val baseUrl: String = appConfig.serviceBaseUrl

  override val kenshooRegistry: MetricRegistry = metrics.defaultRegistry

  def getSmth()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] =
    monitor(s"ConsumedAPI-$servicenameHyphen$-smth-GET") {
      http.GET[HttpResponse](new URL(baseUrl + "/$servicenameHyphen$/dosmth").toExternalForm)
    }

  def postSmth(
    model: $servicenameCamel$FrontendModel)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] =
    monitor(s"ConsumedAPI-$servicenameHyphen$-smth-POST") {
      http.POST[$servicenameCamel$FrontendModel, HttpResponse](
        new URL(baseUrl + "/$servicenameHyphen$/dosmth").toExternalForm,
        model)
    }

}
