package client

import api.ServiceGrpc
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver

object Main {

  def main(args: Array[String]): Unit = {

    val channel =
      ManagedChannelBuilder
        .forTarget("localhost:8080")
        .usePlaintext()
        .build()

    val client = ServiceGrpc
      .newAsyncClient(channel)
    client.GreeterApi.sayHello(
      "Airframe gRPC",
      new StreamObserver[String] {
        def onNext(v: String): Unit = {
          println(s"""response is "$v""")
        }
        def onError(t: Throwable): Unit = {
          println("boom!!!")
        }
        def onCompleted(): Unit = {
          println("completed!!!")
        }
      }
    )
  }
}
