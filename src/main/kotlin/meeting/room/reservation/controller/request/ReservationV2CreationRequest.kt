package meeting.room.reservation.controller.request

import java.time.LocalDateTime

data class ReservationV2CreationRequest(
    val title: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val creatorId: Int,
    val roomId: Int,
    val participants: Set<Int>,
) {
    fun getUserIds(): Set<Int> {
        return participants + creatorId
    }
}
