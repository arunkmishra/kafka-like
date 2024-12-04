package com.arun.queue.models

class Message[T] {
  private var msg: T = _

  def setMessage(message: T): Unit = {
    msg = message
  }

  def getMessage: T = msg
}