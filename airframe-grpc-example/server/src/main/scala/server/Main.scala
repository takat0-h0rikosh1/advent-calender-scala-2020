package server

import wvlet.airframe.http.Router
import wvlet.airframe.http.grpc.gRPC

class GreeterApiImpl extends api.GreeterApi {
  def sayHello(message: String): String = s"Hello ${message}!"
}

object Main {

  val router: Router = Router.add[GreeterApiImpl]

  def main(args: Array[String]): Unit = {
    gRPC.server
      .withRouter(router)
      .withPort(8080)
      .start { server =>
        server.awaitTermination
      }

  }
}
