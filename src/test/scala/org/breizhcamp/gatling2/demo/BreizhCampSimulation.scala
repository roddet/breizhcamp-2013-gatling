package org.breizhcamp.gatling2.demo

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import io.gatling.http.Headers.Values._
import scala.concurrent.duration._
import bootstrap._
import assertions._

class BreizhCampSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8000")
		.acceptHeader("application/json, text/plain, */*")
		.acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.3")
		.acceptEncodingHeader("gzip,deflate,sdch")
		.acceptLanguageHeader("en-US,en;q=0.8")
		.connection("keep-alive")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Ubuntu/12.10 Chromium/25.0.1364.172 Chrome/25.0.1364.172 Safari/537.22")

	val backendBaseURL = """http://localhost:9000"""
	
	// Get datas from src/test/resources/data
	val registerDatas = csv("register.csv").circular;
	
	val scn = scenario("JCertif 2013")
		.feed(registerDatas)
		.exec(http("/")
			.get("""/""")
			.headers(Headers.headers_1))
		.pause(304 milliseconds)
		.exec(http("/header.html")
			.get("""/directives/header/header.html""")
			.headers(Headers.headers_2))
		.exec(http("/menu.html")
			.get("""/directives/menu/menu.html""")
			.headers(Headers.headers_2))
		.exec(http("/footer.html")
			.get("""/directives/footer/footer.html""")
			.headers(Headers.headers_2))
		.pause(11 milliseconds)
		.exec(http("/home.html")
			.get("""/pages/home/home.html""")
			.headers(Headers.headers_2))
		.pause(71 milliseconds)
		.exec(http("/sponsor/list")
			.get(backendBaseURL + """/sponsor/list?jsonp=angular.callbacks._0""")
			.headers(Headers.headers_6))
		.exec(http("/participant/register")
		    .post(backendBaseURL + """/participant/register""")
		    .elFileBody("register.js").asJSON) // Get fileBody from src/test/resources/request-bodies

	setUp(scn.inject(ramp(5000 users) over (20 seconds))).protocols(httpProtocol)
}