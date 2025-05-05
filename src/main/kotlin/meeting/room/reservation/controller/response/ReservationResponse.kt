package meeting.room.reservation.controller.response

import meeting.room.reservation.domain.Reservation
import meeting.room.reservation.domain.ReservationParticipant

data class ReservationResponse(
    val id: Int,
    val title: String,
    val creator: String,
    val participants: List<ParticipantResponse>,
) {
    constructor(reservation: Reservation, participants: List<ReservationParticipant>) : this(
        id = reservation.id,
        title = reservation.title,
        creator = reservation.creator.name,
        participants = participants.map { participant -> ParticipantResponse(participant) },
    )
}
