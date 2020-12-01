package controllers

import example.myapp.helloworld.grpc.GreeterServiceClient
import example.myapp.helloworld.grpc.HelloRequest
import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class GreeterController @Inject()(
  implicit greeterClient: GreeterServiceClient,
  cc: ControllerComponents,
  exec: ExecutionContext,
) extends AbstractController(cc) {

  def sayHello(name: String): Action[AnyContent] = Action.async {
    greeterClient
      .sayHello(HelloRequest(name))
      .map { reply =>
        Ok(s"response: ${reply.message}")
      }
  }

}
