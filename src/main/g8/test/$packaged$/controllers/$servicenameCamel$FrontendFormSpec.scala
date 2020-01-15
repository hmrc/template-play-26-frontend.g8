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

import $package$.models.{$servicenameCamel$FrontendModel}
import uk.gov.hmrc.play.test.UnitSpec

class $servicenameCamel$FrontendFormSpec extends UnitSpec {

  "$servicenameCamel$FrontendForm" should {

    "bind some input fields and return $servicenameCamel$FrontendModel and fill it back" in {
      val form = $servicenameCamel$FrontendController.$servicenameCamel$FrontendForm

      val value = $servicenameCamel$FrontendModel(
        parameter1 = "SomeValue",
        parameter2 = None,
        telephoneNumber = None,
        emailAddress = None)

      val fieldValues = Map("parameter1" -> "SomeValue")

      form.bind(fieldValues).value shouldBe Some(value)
      form.fill(value).data shouldBe fieldValues
    }

    "bind all input fields and return $servicenameCamel$FrontendModel and fill it back" in {
      val form = $servicenameCamel$FrontendController.$servicenameCamel$FrontendForm

      val value = $servicenameCamel$FrontendModel(
        parameter1 = "SomeValue",
        parameter2 = Some("AA1 1AA"),
        telephoneNumber = Some("098765321"),
        emailAddress = Some("foo@bar.com"))

      val fieldValues = Map(
        "parameter1"      -> "SomeValue",
        "parameter2"      -> "AA1 1AA",
        "telephoneNumber" -> "098765321",
        "emailAddress"    -> "foo@bar.com")

      form.bind(fieldValues).value shouldBe Some(value)
      form.fill(value).data shouldBe fieldValues
    }
  }
}
