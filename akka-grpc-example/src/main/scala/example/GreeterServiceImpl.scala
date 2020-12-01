package example

import example.myapp.helloworld.grpc.{GreeterService, HelloReply, HelloRequest}

import scala.concurrent.{ExecutionContext, Future}

class GreeterServiceImpl(implicit ec: ExecutionContext) extends GreeterService {

  override def sayHello(in: HelloRequest): Future[HelloReply] =
    Future.successful(HelloReply(message = s"Hello ${in.name}"))

}
