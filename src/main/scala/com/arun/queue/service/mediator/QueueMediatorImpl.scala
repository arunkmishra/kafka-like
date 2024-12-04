package com.arun.queue.service.mediator

import com.arun.queue.models.Message
import com.arun.queue.models.impl.TopicImpl
import com.arun.queue.models.interfaces.Topic

import java.util.concurrent.ConcurrentHashMap
import scala.collection.mutable
import scala.jdk.CollectionConverters._

// Singleton object for QueueMediator
class QueueMediatorImpl[T] extends QueueMediator[T] {

  private val topicMap: mutable.Map[String, Topic[T]] =
    new ConcurrentHashMap[String, Topic[T]]().asScala

  def publishToTopic(topicName: String, msg: Message[T]): Unit = {
    addTopic(topicName)
    topicMap(topicName).addMessage(msg)
  }

  def addTopic(topicName: String): Unit = {
    if (!topicMap.contains(topicName)) {
      topicMap(topicName) = new TopicImpl()
    }
  }

  def readMsgIfPresent(topicName: String, offset: Int): Option[Message[T]] = {
    topicMap.get(topicName).flatMap(_.readMsgIfPresent(offset))
  }
}
