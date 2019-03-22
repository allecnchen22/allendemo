package com.example.allendemo;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;


@RequestMapping("/hello")
@RestController
public class HelloController {

	@RequestMapping("/index")
	public String index() {
		int a = 1/ 0;
		return "Hello World";
	}

	@RequestMapping("sendalert")
	public String send() {
		CollectorRegistry registry = new CollectorRegistry();
		Gauge duration = Gauge.build().name("my_batch_job_duration_seconds")
				.help("Duration of my batch job in seconds.").register(registry);
		Gauge.Timer durationTimer = duration.startTimer();
		try {
			// Your code here.
			Thread.sleep(3000);
			// This is only added to the registry after success,
			// so that a previous success in the Pushgateway isn't overwritten on failure.
			Gauge lastSuccess = Gauge.build().name("my_batch_job_last_success")
					.help("Last time my batch job succeeded, in unixtime.").register(registry);
			lastSuccess.setToCurrentTime();
		}   catch (Exception ex) {
			ex.printStackTrace();
		}
			finally {
			durationTimer.setDuration();
			PushGateway pg = new PushGateway("localhost:9091");
			try {
				pg.pushAdd(registry, "my_batch_job");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Send my_batch_job";
	}
	     
	     
}
