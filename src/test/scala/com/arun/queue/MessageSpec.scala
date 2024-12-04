package com.arun.queue

import com.arun.queue.models.Message
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MessageSpec extends AnyFlatSpec with Matchers {

  it should "store and retrieve a message of any type" in {
    val intMsg = new Message[Int]
    intMsg.setMessage(123)
    intMsg.getMessage should be(123)

    val strMsg = new Message[String]
    strMsg.setMessage("Hello")
    strMsg.getMessage should be("Hello")
  }
}
