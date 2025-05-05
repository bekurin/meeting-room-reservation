package meeting.room.reservation.controller

import meeting.room.reservation.controller.request.ReservationV2CreationRequest
import meeting.room.reservation.controller.response.ReservationResponse
import meeting.room.reservation.service.ReservationV2Service
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/reservations")
class ReservationV2Controller(
    private val reservationV2Service: ReservationV2Service,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun create(
        @RequestBody request: ReservationV2CreationRequest,
    ): ReservationResponse {
        return reservationV2Service.createReservation(request)
    }
}
