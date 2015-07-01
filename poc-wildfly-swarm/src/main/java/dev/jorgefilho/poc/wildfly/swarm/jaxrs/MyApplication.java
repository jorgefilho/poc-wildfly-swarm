package dev.jorgefilho.poc.wildfly.swarm.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class MyApplication extends Application {
	public MyApplication() {
    }
}
