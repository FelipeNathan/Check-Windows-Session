package br.com.checkwindowssession.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean

@SpringBootTest
class WindowsSessionServiceTests {

    @SpyBean
    lateinit var service: WindowsSessionService

    @Test
    fun shoudNotThrowException() {
        assertDoesNotThrow { service.getUsersInSession() }
    }

    @Test
    fun shouldHaveOneUser() {
        Mockito.`when`(service.executeWindowsCmd()).then { "TITLE\n\rrdp-tcp" }

        val dto = service.getUsersInSession()
        assert(dto.rows.size == 1)
    }

    @Test
    fun shouldHaveNoUsers() {
        Mockito.`when`(service.executeWindowsCmd()).then { "TITLE\n\r1" }

        val dto = service.getUsersInSession()
        assert(dto.rows.isEmpty())
    }
}