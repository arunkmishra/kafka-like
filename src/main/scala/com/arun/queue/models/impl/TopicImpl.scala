package com.arun.queue.models.impl

import com.arun.queue.models.Message
import com.arun.queue.models.interfaces.Topic

import java.util.concurrent.locks.ReentrantLock
import scala.collection.mutable.ListBuffer

class TopicImpl[T] extends Topic[T] {

  private val msges: ListBuffer[Message[T]] = ListBuffer.empty
  private val msgWriterReentrantLock = new ReentrantLock()

  private def getReentrantWriterLock: ReentrantLock = msgWriterReentrantLock

  def addMessage(message: Message[T]): Unit = {
    getReentrantWriterLock.lock()
    try {
      msges.append(message)
    } finally {
      getReentrantWriterLock.unlock()
    }
  }

  def readMsgIfPresent(offset: Int): Option[Message[T]] = {
    if (msges.length <= offset) {
      None
    } else {
      getReentrantWriterLock.lock()
      try {
        Some(msges(offset))
      } finally {
        getReentrantWriterLock.unlock()
      }
    }
  }

  override def checkIfAnyMsgExist(offset: Int): Boolean = {
    msges.nonEmpty && msges.length > offset
  }
}
