package dev.jorgefilho.poc.wildfly.swarm;

import org.wildfly.swarm.container.Container;

public class App {
	public static void main(String[] args) throws Exception {
        Container container = new Container();

        container.subsystem(new MessagingFraction()
                        .server(
                                new MessagingServer()
                                        .enableInVmConnector()
                                        .topic("my-topic")
                                        .queue("my-queue")
                        )
        );

        // Start the container
        container.start();

        JaxRsDeployment appDeployment = new JaxRsDeployment();
        appDeployment.addResource(MyResource.class);

        // Deploy your JAX-RS app
        container.deploy(appDeployment);

        // Create an MSC deployment
        ServiceDeployment deployment = new ServiceDeployment();
        deployment.addService(new MyService("/jms/topic/my-topic" ) );

        // Deploy the services
        container.deploy( deployment );
    }
}
