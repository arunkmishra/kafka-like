package com.arun.queue.models.interfaces

// Define a trait to represent the iProducer interface
trait Producer[T] {

  // Publish a message to a topic
  def publishToTopic(topicName: String, msg: T): Unit
}
