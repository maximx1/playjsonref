package controllers

import play.api._
import play.api.mvc._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.github.tototoshi.play2.json4s.jackson._

case class Person(name: String, age: Int, things: List[Thing])
case class Thing(id: Int, description: String)

object Application extends Controller with Json4s {

  implicit val formats = DefaultFormats

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def apiTest = Action(json) { implicit request =>
    Ok(views.html.displayPerson(request.body.extract[Person]))
  }

}