package com.arun.queue.service.mediator

import com.arun.queue.models.Message

trait QueueMediator[T] {
  // Will have all the topics by topicName
  def publishToTopic(topicName: String, msg: Message[T]): Unit

  def addTopic(topicName: String): Unit

  def readMsgIfPresent(topicName: String, offset: Int): Option[Message[T]]
}
