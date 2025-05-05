package meeting.room.reservation.controller

import meeting.room.reservation.controller.request.ReservationV1CreationRequest
import meeting.room.reservation.controller.response.ReservationResponse
import meeting.room.reservation.service.ReservationV1Service
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reservations")
class ReservationV1Controller(
    private val reservationV1Service: ReservationV1Service,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun create(
        @RequestBody request: ReservationV1CreationRequest,
    ): ReservationResponse {
        return reservationV1Service.createReservation(request)
    }
}
