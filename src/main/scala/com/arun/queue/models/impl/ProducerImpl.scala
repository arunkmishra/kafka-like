package com.arun.queue.models.impl

import com.arun.queue.models.Message
import com.arun.queue.models.interfaces.Producer
import com.arun.queue.service.mediator.QueueMediator

class ProducerImpl[T](name: String, queueMediator: QueueMediator[T])
    extends Producer[T] {

  override def publishToTopic(topicName: String, msg: T): Unit = {
    val msgObj = new Message[T]()
    msgObj.setMessage(msg)
    println(s"[$name] Msg: $msg has been published to topic: $topicName")
    queueMediator.publishToTopic(topicName, msgObj)
  }
}
