/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package $package$.controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import play.api.{Configuration, Environment}
import $package$.connectors.{FrontendAuthConnector, $servicenameCamel$Connector}
import $package$.models.$servicenameCamel$FrontendModel
import $package$.views.html.{start => startPage, _}
import $package$.views.html.main_template
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import uk.gov.hmrc.play.views.html.helpers.{ErrorSummary, FormWithCSRF, Input}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class $servicenameCamel$FrontendController @Inject()(
  override val messagesApi: MessagesApi,
  $servicenamecamel$Connector: $servicenameCamel$Connector,
  val authConnector: FrontendAuthConnector,
  val env: Environment,
  input: Input,
  form: FormWithCSRF,
  errorSummary: ErrorSummary,
  mainTemplate: main_template,
  mcc: MessagesControllerComponents)(
  implicit val configuration: Configuration,
  ec: ExecutionContext)
    extends FrontendController(mcc) with I18nSupport with AuthActions {

  import $servicenameCamel$FrontendController._

  def root: Action[AnyContent] = Action { implicit request =>
    Redirect(routes.$servicenameCamel$FrontendController.start().url)
  }

  def start: Action[AnyContent] = Action { implicit request =>
    Ok(new startPage(mainTemplate)())
  }

  def show$servicenameCamel$FrontendForm: Action[AnyContent] = Action.async { implicit request =>
    withAuthorisedAsAgent { arn =>
      Future.successful(
        Ok(new $servicenamecamel$FrontendForm(input, form, errorSummary, mainTemplate)(
          $servicenameCamel$FrontendForm)))
    }
  }

  def $servicenamecamel$FrontendForm = Action.async { implicit request =>
    withAuthorisedAsAgent { arn =>
      $servicenameCamel$FrontendForm
        .bindFromRequest()
        .fold(
          formWithErrors => {
            Future.successful(
              Ok(new $servicenamecamel$FrontendForm(input, form, errorSummary, mainTemplate)(
                formWithErrors)))
          },
          data => {
            Future.successful(
              Ok(new summary(mainTemplate)($servicenameCamel$FrontendForm.fill(data))))
          }
        )
    }
  }

}

object $servicenameCamel$FrontendController {

  import $package$.controllers.FieldMappings._

  val $servicenameCamel$FrontendForm = Form[$servicenameCamel$FrontendModel](
    mapping(
      "parameter1"      -> validName,
      "parameter2"      -> optional(postcode),
      "telephoneNumber" -> telephoneNumber,
      "emailAddress"    -> emailAddress)($servicenameCamel$FrontendModel.apply)(
      $servicenameCamel$FrontendModel.unapply))
}
