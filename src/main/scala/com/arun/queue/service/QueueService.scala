package com.arun.queue.service

import com.arun.queue.models.interfaces.{Consumer, Producer}

trait QueueService[T] {
  // This trait will define the methods to be implemented by the Queue Service

  def createTopic(topicName: String): Unit

  def createProducer(producerName: String): Producer[T]

  def createConsumer(consumerName: String): Consumer[T]
}