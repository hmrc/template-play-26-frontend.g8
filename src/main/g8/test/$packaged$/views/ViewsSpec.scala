package $package$.views

import javax.inject.Inject
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.OneAppPerSuite
import play.api.Configuration
import play.api.i18n.Messages
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.twirl.api.Html
import $package$.controllers.$servicenameCamel$FrontendController
import $package$.models.$servicenameCamel$FrontendModel
import $package$.views.html._
import uk.gov.hmrc.play.test.UnitSpec
import uk.gov.hmrc.play.config.OptimizelyConfig
import uk.gov.hmrc.play.views.html.layouts._
import uk.gov.hmrc.play.config.AssetsConfig
import uk.gov.hmrc.play.config.GTMConfig
import uk.gov.hmrc.play.views.html.helpers.{ErrorSummary, FormWithCSRF, Input, ReportAProblemLink}
import views.html.layouts.GovUkTemplate
import play.api.i18n.Lang

class ViewsSpec @Inject()(govUkWrapper: govuk_wrapper, mainTemplate: main_template)
    extends UnitSpec with OneAppPerSuite with MockitoSugar {

  implicit val lang: Lang = Lang("eng")

  private val filledForm = $servicenameCamel$FrontendController.$servicenameCamel$FrontendForm.fill(
    $servicenameCamel$FrontendModel(
      parameter1 = "My contact name",
      parameter2 = Some("AA1 1AA"),
      telephoneNumber = Some("9876543210"),
      emailAddress = Some("my@email.com")))

  "error_template view" should {
    "render title, heading and message" in new App {
      val pageTitle = "My custom page title"
      val heading = "My custom heading"
      val message = "My custom message"
      val html = new error_template(govUkWrapper).render(
        pageTitle = pageTitle,
        heading = heading,
        message = message,
        messages = Messages.Implicits.applicationMessages,
        configuration = app.configuration)
      val content = contentAsString(html)
      content should include(pageTitle)
      content should include(heading)
      content should include(message)

      val html2 =
        new error_template(govUkWrapper)
          .f(pageTitle, heading, message)(Messages.Implicits.applicationMessages, app.configuration)
      contentAsString(html2) shouldBe (content)
    }
  }

  "start view" should {
    "render title and messages" in new App {
      val html = new start(mainTemplate)
        .render(request = FakeRequest(), messages = Messages.Implicits.applicationMessages, config = app.configuration)
      val content = contentAsString(html)

      import Messages.Implicits.applicationMessages
      content should include(Messages("start.title"))
      content should include(Messages("start.label"))
      content should include(Messages("start.intro"))
      content should include(Messages("start.helpdesklink.text1"))
      content should include(Messages("start.helpdesklink.text2"))

      val html2 = new start(mainTemplate).f()(FakeRequest(), Messages.Implicits.applicationMessages, app.configuration)
      contentAsString(html2) shouldBe (content)
    }
  }

  "$servicenamecamel$FrontendForm view" should {
    "render title and messages" in new App {
      val input = new Input()
      val form = new FormWithCSRF()
      val errorSummary = new ErrorSummary()
      val html = new $servicenamecamel$FrontendForm(input, form, errorSummary, mainTemplate).render(
        $servicenamecamel$FrontendForm = filledForm,
        request = FakeRequest(),
        messages = Messages.Implicits.applicationMessages,
        config = app.configuration)
      val content = contentAsString(html)

      import Messages.Implicits.applicationMessages
      content should include(Messages("newshinyservice26frontendform.title"))
      content should include(Messages("newshinyservice26frontendform.label"))
      content should include(Messages("newshinyservice26frontendform.intro"))
      content should include(Messages("newshinyservice26frontendform.parameter1.label"))
      content should include(Messages("newshinyservice26frontendform.telephoneNumber.label"))
      content should include(Messages("newshinyservice26frontendform.emailAddress.label"))

      val html2 = new $servicenamecamel$FrontendForm(input, form, errorSummary, mainTemplate)
        .f(filledForm)(FakeRequest(), Messages.Implicits.applicationMessages, app.configuration)
      contentAsString(html2) shouldBe (content)
    }
  }

  "main_template view" should {
    "render all supplied arguments" in new App {
      val sidebar = new Sidebar()
      val article = new Article()
      val view = new main_template(sidebar, article, govUkWrapper)
      val html = view.render(
        title = "My custom page title",
        sidebarLinks = Some(Html("My custom sidebar links")),
        contentHeader = Some(Html("My custom content header")),
        bodyClasses = Some("my-custom-body-class"),
        mainClass = Some("my-custom-main-class"),
        scriptElem = Some(Html("My custom script")),
        mainContent = Html("My custom main content HTML"),
        messages = Messages.Implicits.applicationMessages,
        request = FakeRequest(),
        configuration = app.configuration
      )

      val content = contentAsString(html)
      content should include("My custom page title")
      content should include("My custom sidebar links")
      content should include("My custom content header")
      content should include("my-custom-body-class")
      content should include("my-custom-main-class")
      content should include("My custom script")
      content should include("My custom main content HTML")

      val html2 = view.f(
        "My custom page title",
        Some(Html("My custom sidebar links")),
        Some(Html("My custom content header")),
        Some("my-custom-body-class"),
        Some("my-custom-main-class"),
        Some(Html("My custom script"))
      )(Html("My custom main content HTML"))(Messages.Implicits.applicationMessages, FakeRequest(), app.configuration)
      contentAsString(html2) shouldBe (content)
    }
  }

  "govuk wrapper view" should {
    "render all of the supplied arguments" in new App {
      val config = mock[Configuration]
      val optimizelyConfig = new OptimizelyConfig(config)
      val optimizelySnippet = new OptimizelySnippet(optimizelyConfig)
      val assetsConfig = new AssetsConfig(config)
      val gtmConfig = new GTMConfig(config)
      val gtmSnippet = new GTMSnippet(gtmConfig)
      val head = new Head(optimizelySnippet, assetsConfig, gtmSnippet)
      val headerNav = new HeaderNav()
      val footer = new Footer(assetsConfig)
      val footerLinks = new FooterLinks()
      val serviceInfo = new ServiceInfo()
      val mainContentHeader = new MainContentHeader()
      val mainContent = new MainContent()
      val reportAProblemLink = new ReportAProblemLink()
      val govUkTemplate = new GovUkTemplate()

      val html = new govuk_wrapper(
        head,
        headerNav,
        footer,
        footerLinks,
        serviceInfo,
        mainContentHeader,
        mainContent,
        reportAProblemLink,
        govUkTemplate).render(
        title = "My custom page title",
        mainClass = Some("my-custom-main-class"),
        mainDataAttributes = Some(Html("myCustom=\\"attributes\\"")),
        bodyClasses = Some("my-custom-body-class"),
        sidebar = Html("My custom sidebar"),
        contentHeader = Some(Html("My custom content header")),
        mainContent = Html("My custom main content"),
        serviceInfoContent = Html("My custom service info content"),
        scriptElem = Some(Html("My custom script")),
        gaCode = Seq("My custom GA code"),
        messages = Messages.Implicits.applicationMessages,
        configuration = app.configuration
      )

      val content = contentAsString(html)
      content should include("My custom page title")
      content should include("my-custom-main-class")
      content should include("myCustom=\\"attributes\\"")
      content should include("my-custom-body-class")
      content should include("My custom sidebar")
      content should include("My custom content header")
      content should include("My custom main content")
      content should include("My custom service info content")
      content should include("My custom script")

      val html2 = new govuk_wrapper(
        head,
        headerNav,
        footer,
        footerLinks,
        serviceInfo,
        mainContentHeader,
        mainContent,
        reportAProblemLink,
        govUkTemplate).f(
        "My custom page title",
        Some("my-custom-main-class"),
        Some(Html("myCustom=\\"attributes\\"")),
        Some("my-custom-body-class"),
        Html("My custom sidebar"),
        Some(Html("My custom content header")),
        Html("My custom main content"),
        Html("My custom service info content"),
        Some(Html("My custom script")),
        Seq("My custom GA code")
      )(Messages.Implicits.applicationMessages, app.configuration)
      contentAsString(html2) shouldBe (content)
    }
  }
}
