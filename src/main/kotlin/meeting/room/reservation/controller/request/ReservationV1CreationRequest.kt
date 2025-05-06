package meeting.room.reservation.controller.request

import meeting.room.reservation.domain.Reservation
import meeting.room.reservation.domain.Room
import meeting.room.reservation.domain.TimePeriod
import meeting.room.reservation.domain.User
import java.time.LocalDateTime

data class ReservationV1CreationRequest(
    val title: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val creatorId: Int,
    val roomId: Int,
    val participants: List<Int>,
) {
    fun toReservation(creator: User, room: Room): Reservation {
        return Reservation(
            title = title,
            room = room,
            creator = creator,
            timePeriod = TimePeriod(startAt, endAt),
        )
    }
}
