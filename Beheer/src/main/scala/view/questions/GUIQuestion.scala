package view.questions

import ast.Stylesheet.Styling
import ast._
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ Evaluator, Value }
import view.{ env, updateEnv }

import scala.language.implicitConversions
import scalafx.beans.binding.{ Bindings, BooleanBinding, StringBinding }
import scalafx.event.subscriptions.Subscription
import scalafx.scene.layout.VBox
import scalafx.scene.text.{ Font, Text }

trait GUIQuestion {
  val question: DisplayQuestion
  val questionStyle: Option[QuestionStyle]

  implicit protected def widgetUpdateHandler(newVal: Value): Unit = updateEnv(question.identifier, newVal)

  val displayBox = new VBox {
    children = Seq(createLabel)
    disable = isDisabled(question)
    visible <== isVisible(question)
  }

  protected val width: Double = questionStyle.flatMap(_.styling.values.collect {
    case Width(value) => value
  }.lastOption.map(_.toDouble)).getOrElse(100.0)

  protected def createValueBinding(c: ComputedQuestion)(changeHandler: Value => Unit): Subscription =
    Bindings.createObjectBinding[Value](() => Evaluator(env.toMap).calculate(c.value), env).onChange((newValue, _, _) => changeHandler(newValue.value))

  protected def computeValue(question: ComputedQuestion): StringBinding =
    Bindings.createStringBinding(() => Evaluator(env.toMap).calculate(question.value).toString, env)

  private def createLabel: Text = {
    questionStyle match {
      case Some(d) =>
        val text = new Text(question.label)
        text.font_=(getFont(d.styling))
        text.fill_=(getFill(d.styling))
        text
      case None => new Text(question.label)
    }
  }

  private def getFill(styling: Styling) = styling.values.collectFirst {
    case Color(value) => scalafx.scene.paint.Color.web(value)
  }.getOrElse(scalafx.scene.paint.Color.Black)

  private def getFont(styling: Styling): Font = {
    val font = styling.values.collectFirst { case ast.Font(f) => f }
    val size = styling.values.collectFirst { case FontSize(s) => s }

    (font, size) match {
      case (Some(f), Some(s)) => Font.font(f, s.toDouble)
      case (Some(f), None) => Font.font(f)
      case (None, Some(s)) => Font.font(s.toDouble)
      case (None, None) => Font.font(null)
    }
  }

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  private def isVisible(question: DisplayQuestion): BooleanBinding =
    Bindings.createBooleanBinding(() => Evaluator(env.toMap).show(question.displayCondition), env)
}