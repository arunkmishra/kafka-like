package com.arun.queue.models.impl

import com.arun.queue.models.interfaces.Consumer
import com.arun.queue.service.mediator.QueueMediator

import java.util.concurrent.ConcurrentHashMap
import scala.collection.mutable.ListBuffer

class ConsumerImpl[T](consumerName: String, queueMediator: QueueMediator[T])
    extends Consumer[T] {

  private var listeningToTopic = true
  private val topicList: ListBuffer[String] = ListBuffer.empty
  private val topicVsOffset: ConcurrentHashMap[String, Int] =
    new ConcurrentHashMap()
  threadInit()

  private def setTopicOffset(topicName: String, offset: Int): Unit = {
    topicVsOffset.put(topicName, offset)
  }

  def getTopicOffset(topicName: String): Int = {
    topicVsOffset.getOrDefault(topicName, 0)
  }

  private def addToTopicList(topicName: String): Unit = {
    topicList.append(topicName)
  }

  override def subToTopic(topicName: String): Unit = {
    addToTopicList(topicName)
    topicVsOffset.put(topicName, 0)
  }

  private def consumeMsg(msg: T, offset: Int): Unit = {
    println(
      s"Msg: $msg has been consumed by consumer: $consumerName at offset: $offset" +
        s" with current thread: ${Thread.currentThread().getName}"
    )
  }

  private def consumerRunner(): Unit = {
    while (listeningToTopic) {
      topicList.toList.foreach { topicName =>
        val curOffset = getTopicOffset(topicName)
        val msg = queueMediator.readMsgIfPresent(topicName, curOffset)
        if (msg.isDefined) {
          consumeMsg(msg.get.getMessage, curOffset)
          setTopicOffset(topicName, curOffset + 1)
        }
      }
      try {
        Thread.sleep(100)
      } catch {
        case e: InterruptedException => println(s"Error: $e")
      }
    }
  }

  private def threadInit(): Unit = {
    val thread = new Thread(() => consumerRunner())
    thread.start()
  }
  override def stopConsumer(): Unit = {
    listeningToTopic = false
  }

}
