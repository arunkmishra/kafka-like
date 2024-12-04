package com.arun.queue.models.interfaces

// Define a trait to represent the iConsumer interface
trait Consumer[T] {

  // Subscribe to a topic
  def subToTopic(topicName: String): Unit

  def getTopicOffset(topicName: String): Int

  def stopConsumer(): Unit
}
