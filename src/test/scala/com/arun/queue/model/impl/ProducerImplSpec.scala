package com.arun.queue.model.impl

import com.arun.queue.models.impl.ProducerImpl
import com.arun.queue.service.mediator.QueueMediatorImpl
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ProducerImplSpec extends AnyFlatSpec with Matchers {

  it should "publish messages to a given topic" in {
    val queueMediator = new QueueMediatorImpl[String]
    val producer = new ProducerImpl[String]("testProducer", queueMediator)

    val topicName = "testTopic"
    val message = "Hello, World!"

    queueMediator.addTopic(topicName)
    producer.publishToTopic(topicName, message)

    val readMsg = queueMediator.readMsgIfPresent(topicName, 0)
    readMsg should not be empty
    readMsg.get.getMessage should be("Hello, World!")
  }
}
