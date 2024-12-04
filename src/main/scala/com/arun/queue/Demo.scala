package com.arun.queue

import com.arun.queue.service.QueueServiceImpl

object Demo extends App {

  private val TOPIC_NAME1 = "topic1"
  private val TOPIC_NAME2 = "topic2"
  private val TOPIC_NAME3 = "topic3"
  private val PRODUCER1 = "producer1"
  private val PRODUCER2 = "producer2"
  private val CONSUMER1 = "consumer1"
  private val CONSUMER2 = "consumer2"

  private val queueService = new QueueServiceImpl[String]

  private val producer1 = queueService.createProducer(PRODUCER1)
  private val producer2 = queueService.createProducer(PRODUCER2)

  private val consumer1 = queueService.createConsumer(CONSUMER1)
  private val consumer2 = queueService.createConsumer(CONSUMER2)

  // Publish messages to topics
  producer1.publishToTopic(TOPIC_NAME1, "message1")
  producer1.publishToTopic(TOPIC_NAME1, "message2")
  producer2.publishToTopic(TOPIC_NAME2, "message3")
  producer1.publishToTopic(TOPIC_NAME1, "message4")

  // Subscribe consumer to topics
  consumer1.subToTopic(TOPIC_NAME1)
  consumer1.subToTopic(TOPIC_NAME2)

  // Run the consumer
  Thread.sleep(1000) // to slow down
  producer1.publishToTopic(TOPIC_NAME1, "message6")
  consumer2.subToTopic(TOPIC_NAME1) // read all messages at once including message 6
  consumer2.subToTopic(TOPIC_NAME3) // wont fail just listen to new message

  Thread.sleep(100)
  consumer1.stopConsumer()
  consumer2.stopConsumer()
}
