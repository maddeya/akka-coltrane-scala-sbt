package net.maddeya

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

case object Tick
case object Tock
case object Get

class Counter extends Actor {
  var count = 0

  def receive = {
    case Tick => count += 1
    case Tock => count -= 1
    case Get  => sender ! count
  }
}

object TickTock extends App {
  import ExecutionContext.Implicits.global
  
  val system = ActorSystem("TickTock")

  val counter = system.actorOf(Props[Counter])

  counter ! Tick
  counter ! Tick
  counter ! Tock
  counter ! Tick

  implicit val timeout = Timeout(5.seconds)

  (counter ? Get) onSuccess {
    case count => println("Count is " + count)
  }

  system.shutdown()
}
