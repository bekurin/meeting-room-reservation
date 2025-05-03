package meeting.room.reservation.controller

import meeting.room.reservation.controller.response.HelloResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(
        @RequestParam username: String
    ): HelloResponse {
        return HelloResponse(username)
    }
}