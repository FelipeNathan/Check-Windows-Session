package br.com.checkwindowssession.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.ConfigurableWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class Configuration: WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Autowired
    lateinit var environment: Environment

    override fun customize(factory: ConfigurableWebServerFactory?) {

        val property = environment.getProperty("server.port") ?: "3080"
        factory?.setPort(Integer.parseInt(property))
    }
}