package com.marvastsi.spring.security.x509.application.web.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
class UserController {
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = ["/user"])
    fun user(model: Model, principal: Principal): String? {
        val currentUser = (principal as Authentication).principal as UserDetails
        model.addAttribute("username", currentUser.username)
        return "user"
    }
}