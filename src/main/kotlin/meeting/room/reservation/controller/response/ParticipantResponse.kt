package meeting.room.reservation.controller.response

import meeting.room.reservation.domain.ReservationParticipant

data class ParticipantResponse(
    val id: Int,
    val name: String,
) {
    constructor(participant: ReservationParticipant) : this(
        id = participant.user.id,
        name = participant.user.name,
    )
}
