package br.com.checkwindowssession

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CheckWindowsSessionApplication

fun main(args: Array<String>) {
    runApplication<CheckWindowsSessionApplication>(*args)
}
