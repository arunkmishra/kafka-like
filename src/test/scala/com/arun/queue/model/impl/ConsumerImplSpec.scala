package com.arun.queue.model.impl

import com.arun.queue.models.impl.{ConsumerImpl, ProducerImpl}
import com.arun.queue.service.mediator.QueueMediatorImpl
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ConsumerImplSpec
    extends AnyFlatSpec
    with Matchers
    with BeforeAndAfterEach {

  private var queueMediator: QueueMediatorImpl[String] = _
  private var consumer: ConsumerImpl[String] = _
  private var producer: ProducerImpl[String] = _

  override def beforeEach(): Unit = {
    queueMediator = new QueueMediatorImpl[String]
    consumer = new ConsumerImpl[String]("testConsumer", queueMediator)
    producer = new ProducerImpl[String]("testProducer", queueMediator)
  }

  "ConsumerImpl" should "subscribe to a topic and consume messages correctly" in {
    val topicName = "testTopic"
    consumer.subToTopic(topicName)

    // Publish messages to the topic
    producer.publishToTopic(topicName, "message1")
    producer.publishToTopic(topicName, "message2")
    Thread.sleep(500) // This delay is arbitrary and depends on your environment
    consumer.getTopicOffset(topicName) shouldBe 2
    producer.publishToTopic(topicName, "message3")
    Thread.sleep(500)
    consumer.getTopicOffset(topicName) shouldBe 3
  }

  override def afterEach(): Unit = consumer.stopConsumer()
}
