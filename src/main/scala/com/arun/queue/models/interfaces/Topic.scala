package com.arun.queue.models.interfaces

import com.arun.queue.models.Message

trait Topic[T] {

  // Add msg to the topic
  def addMessage(msg: Message[T]): Unit

  // Check if any msg exists to read from it
  def checkIfAnyMsgExist(offset: Int): Boolean

  def readMsgIfPresent(offset: Int): Option[Message[T]]
}