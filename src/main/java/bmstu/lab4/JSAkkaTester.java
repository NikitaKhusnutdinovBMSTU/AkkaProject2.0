package bmstu.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import java.util.concurrent.CompletionStage;
import scala.concurrent.Future;

public class JSAkkaTester extends AllDirectives{

    static ActorRef manager;

    public static void main(String[] args) throws Exception{

        ActorSystem system = ActorSystem.create("routes");
        manager = system.actorOf(Props.create(Manager.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        JSAkkaTester app = new JSAkkaTester();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.jsTesterRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );

        System.out.println("Server online on localhost:8080/\n PRESS ANY KEY TO STOP");
        System.in.read();

        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());

    }

    private Route jsTesterRoute(){

        return concat(
                get(
                        () -> parameter("packageId", (packageId) ->
                            {
                                Future<Object> result = Patterns.ask(manager,
                                        new GetMessage(Integer.parseInt(packageId)),
                                        5000);
                                return completeOKWithFuture(result, Jackson.marshaller());
                            }
                        )
                ),
                post(
                        () -> entity(Jackson.unmarshaller(PackageDecoded.class),
                                msg -> {
                                    manager.tell(msg, ActorRef.noSender());
                                    return complete("Server is working");
                                })));
    }

}
