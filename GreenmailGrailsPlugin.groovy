/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import grails.plugin.greenmail.GreenMail
import com.icegreen.greenmail.util.ServerSetupTest
import com.icegreen.greenmail.util.ServerSetup

class GreenmailGrailsPlugin {

	def title = "Greenmail Plugin for Grails"
	def description = "Provides a wrapper around GreenMail (http://www.icegreen.com/greenmail/) and provides a view that displays 'sent' messages"
	def documentation = "http://grails.org/plugin/greenmail"
	
	def version = "1.3-SNAPSHOT"
	def grailsVersion = "1.1.1 > *"
	def dependsOn = [:]

	def author = "Mike Hugo"
	def authorEmail = "mike@piragua.com"

	def pluginExcludes = [
		"grails-app/views/error.gsp"
	]

	def scopes = [excludes: 'war']
	
	def doWithSpring = {
		def smtpPort = application.config.greenmail.ports.smtp ?: ServerSetupTest.SMTP.port
		def smtp = new ServerSetup(smtpPort, null, "smtp")
	
		greenMail(GreenMail, [smtp] as ServerSetup[]) {
			it.initMethod = 'start'
			it.destroyMethod = 'stop'
		}
	}

}
