package net.maddeya

import akka.testkit.{ TestKit, TestActorRef, ImplicitSender }
import akka.actor.{ Actor, ActorRef, ActorSystem, Props }
import scala.concurrent.duration._
import scala.concurrent.Await
import akka.pattern.ask
import org.scalatest.{ BeforeAndAfterAll, WordSpec }
import org.scalatest.matchers.ShouldMatchers

class TickTockSpec extends TestKit(ActorSystem("TestTickTockSystem"))
  with ImplicitSender
  with WordSpec
  with ShouldMatchers
  with BeforeAndAfterAll {

  override def afterAll {
    system.shutdown()
  }

  "[TestActorRef] A CounterActor" should {
    "increase counter by 1 when receiving Tick" in {
      val actorRef = TestActorRef(Props[Counter])
      val actor: Counter = actorRef.underlyingActor
      val before: Int = actor.count
      actorRef ! Tick
      val after: Int = actor.count
      after should be(before + 1)
    }

    "decrease counter by 1 when receiving Tock" in {
      val actorRef = TestActorRef(Props[Counter])
      val actor: Counter = actorRef.underlyingActor
      val before: Int = actor.count
      actorRef ! Tock
      val after: Int = actor.count
      after should be(before - 1)
    }

    "have an unchanged counter when receiving first Tick and then Tock" in {
      val actorRef = TestActorRef(Props[Counter])
      val actor: Counter = actorRef.underlyingActor
      val before: Int = actor.count
      actorRef ! Tick
      actorRef ! Tock
      val after: Int = actor.count
      after should be(before)
    }

    "have an unchanged counter when receiving first Tock and then Tick" in {
      val actorRef = TestActorRef(Props[Counter])
      val actor: Counter = actorRef.underlyingActor
      val before: Int = actor.count
      actorRef ! Tock
      actorRef ! Tick
      val after: Int = actor.count
      after should be(before)
    }

    "decrease counter upon receiving Tock" in {
      val actorRef = TestActorRef(Props[Counter])
      val actor: Counter = actorRef.underlyingActor
      actor.count = 134
      actorRef ! Tock
      actor.count should be(133)
    }
  }

  "[system.actorOf(Props[Counter])] A CounterActor" should {
    "after receiving two Tick in a row have a counter of 2" in {
      val actorRef = system.actorOf(Props[Counter])
      actorRef ! Tick
      actorRef ! Tick
      actorRef ! Get
      expectMsg(2)
    }
  }

  "[system.actorOf(Props(new Counter))] A CounterActor" should {
    "have an internal state of 2 after receiving two Tick in a row" in {
      val actorRef = system.actorOf(Props(new Counter))
      actorRef ! Tick
      actorRef ! Tick
      actorRef ! Get
      expectMsg(2)
    }
  }
}




