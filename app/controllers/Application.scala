package controllers

import play.api._
import play.api.mvc._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.tototoshi.play2.json4s.jackson._

case class FormattedResponse( person: Person)
case class Person(name: String, age: Option[Int], things: List[Thing])
case class Thing(id: Int, description: String)

object Application extends Controller with Json4s {

  implicit val formats = DefaultFormats

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def apiTest = Action(json) { implicit request =>
    Ok(views.html.displayPerson(request.body.extract[Person]))
  }

  def apiReturnTest = Action { implicit request =>
    Ok(Extraction.decompose(samplePerson))
  }

  def apiReturnTestFormatted = Action { implicit request =>
    Ok(Extraction.decompose(sampleFormatted))
  }

  lazy val sampleFormatted = FormattedResponse(samplePerson)
  lazy val samplePerson = Person("Sample name", Some(24), List(Thing(1, "Thing 1"), Thing(2, "Thing 2"), Thing(3, "Thing 4")))
}