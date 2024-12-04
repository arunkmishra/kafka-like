package com.arun.queue.service

import com.arun.queue.models.interfaces.{Consumer, Producer}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class QueueServiceImplSpec extends AnyFlatSpec with Matchers {

  it should "create producers and consumers" in {
    val queueService = new QueueServiceImpl[String]

    val producer = queueService.createProducer("producer1")
    producer shouldBe a[Producer[_]]

    val consumer = queueService.createConsumer("consumer1")
    consumer shouldBe a[Consumer[_]]
  }
}
