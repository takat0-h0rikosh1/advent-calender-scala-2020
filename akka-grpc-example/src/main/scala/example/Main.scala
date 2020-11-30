package example

import akka.actor.ActorSystem
import akka.grpc.scaladsl.{ServerReflection, ServiceHandler}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import com.typesafe.config.ConfigFactory
import example.myapp.helloworld.grpc.{GreeterService, GreeterServiceHandler, HelloReply, HelloRequest}

import scala.concurrent.{ExecutionContext, Future}

class GreeterServiceImpl(implicit ec: ExecutionContext) extends GreeterService {

  override def sayHello(in: HelloRequest): Future[HelloReply] =
    Future.successful(HelloReply(message = s"Hello ${in.name}"))

}

class GreeterServer(system: ActorSystem) {

  def run(): Future[Http.ServerBinding] = {
    implicit val sys: ActorSystem = system
    implicit val ec: ExecutionContext = sys.dispatcher

    val service: PartialFunction[HttpRequest, Future[HttpResponse]] =
      GreeterServiceHandler.partial(new GreeterServiceImpl())

    val reflection: PartialFunction[HttpRequest, Future[HttpResponse]] =
      ServerReflection.partial(List(GreeterService))

    val binding = Http().newServerAt(
      interface = "127.0.0.1",
      port = 8080
    ).bind(ServiceHandler.concatOrNotFound(service, reflection))

    binding.foreach { binding => println(s"gRPC server bound to: ${binding.localAddress}") }
    binding
  }

}

object GreeterServer {

  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load()
    val system = ActorSystem("HelloWorld", conf)
    new GreeterServer(system).run()
  }

}
