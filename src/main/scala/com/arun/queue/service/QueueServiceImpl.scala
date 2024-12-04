package com.arun.queue.service

import com.arun.queue.models.impl.{ConsumerImpl, ProducerImpl}
import com.arun.queue.models.interfaces.{Consumer, Producer}
import com.arun.queue.service.mediator.QueueMediatorImpl

class QueueServiceImpl[T] extends QueueService[T] {
  private val qmd = new QueueMediatorImpl[T]

  def createTopic(topicName: String): Unit = {
    qmd.addTopic(topicName)
  }

  override def createProducer(producerName: String): Producer[T] = {
    new ProducerImpl[T](producerName, qmd)
  }

  def createConsumer(consumerName: String): Consumer[T] = {
    new ConsumerImpl[T](consumerName, qmd)
  }

}
