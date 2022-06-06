package com.project1;
import java.io.File;

import com.project1.controller.RequestMapping;
import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;


public class MainDriver {
	
	static PrometheusMeterRegistry pMR = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	static Javalin app = Javalin.create( config -> {
		config.registerPlugin(new MicrometerPlugin(pMR));
			}
			).start(7070);
	
	
	

	public static void main(String...args) {


	 
	 
	 pMR.config().commonTags("app","monitored-app");
	 
	 new ClassLoaderMetrics().bindTo(pMR);
	 new JvmMemoryMetrics().bindTo(pMR);
	 new JvmGcMetrics().bindTo(pMR);
	 new JvmThreadMetrics().bindTo(pMR);
	 new UptimeMetrics().bindTo(pMR);
	 new ProcessorMetrics().bindTo(pMR);
	 new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(pMR);
		
	 app.get("/prometheus", ctx -> {
		 ctx.result(pMR.scrape());
	 });
	 
	 RequestMapping.configureRoutes(app);
	 

	}
	

}
