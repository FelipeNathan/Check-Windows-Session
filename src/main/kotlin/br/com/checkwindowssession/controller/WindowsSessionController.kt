package br.com.checkwindowssession.controller

import br.com.checkwindowssession.service.WindowsSessionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.inject.Inject

@Controller
class WindowsSessionController {

    @Inject
    lateinit var service: WindowsSessionService

    @GetMapping("/")
    fun index(model: Model): String {

        model.addAttribute("table", service.getUsersInSession())
        return "index"
    }
}