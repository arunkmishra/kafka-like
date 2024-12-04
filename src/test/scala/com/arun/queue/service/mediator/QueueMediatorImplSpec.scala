package com.arun.queue.service.mediator

import com.arun.queue.models.Message
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class QueueMediatorImplSpec extends AnyFlatSpec with Matchers {

  it should "add topics and publish messages to them" in {
    val queueMediator = new QueueMediatorImpl[String]
    val topicName = "testTopic"
    val message = new Message[String]
    message.setMessage("Test Message")

    queueMediator.addTopic(topicName)
    queueMediator.publishToTopic(topicName, message)

    val readMsg = queueMediator.readMsgIfPresent(topicName, 0)
    readMsg should not be empty
    readMsg.get.getMessage should be("Test Message")
  }
}
