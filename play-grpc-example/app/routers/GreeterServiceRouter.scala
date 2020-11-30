package routers

import akka.actor.ActorSystem
import example.myapp.helloworld.grpc.{AbstractGreeterServiceRouter, HelloReply, HelloRequest}
import javax.inject.{Inject, Singleton}

import scala.concurrent.Future

@Singleton
class GreeterServiceRouter @Inject()(implicit actorSystem: ActorSystem)
  extends AbstractGreeterServiceRouter(actorSystem) {

  override def sayHello(in: HelloRequest): Future[HelloReply] =
    Future.successful(HelloReply(s"Hello, ${in.name}!"))

}
