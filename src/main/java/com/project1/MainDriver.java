package com.project1;
import java.io.File;

import com.project1.controller.RequestMapping;


import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;


public class MainDriver {
	
	static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	static Javalin app = Javalin.create( config -> {
		config.registerPlugin(new MicrometerPlugin(registry));
			}
			).start(7070);
	
	
	public static double counter() {
		Counter counter = Counter.builder("total_login_attempts")
				.description("The number of login attempts")
				.tag("purpose", "tracking").register(registry);
		counter.increment(1);
		return counter.count();
	}
	


	public static void main(String...args) {


	 
		
		
	 registry.config().commonTags("app","monitored-app");
	 
	 
	 
	
	 new ClassLoaderMetrics().bindTo(registry);
	 new JvmMemoryMetrics().bindTo(registry);
	 new JvmGcMetrics().bindTo(registry);
	 new JvmThreadMetrics().bindTo(registry);
	 new UptimeMetrics().bindTo(registry);
	 new ProcessorMetrics().bindTo(registry);
	 new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
	 
	
		
	

	 
	
	 RequestMapping.configureRoutes(app);
	 
	 
	 	app.get("/prometheus", ctx -> {
		 
			 ctx.result(registry.scrape());
		 });

	}
	
	 

}
