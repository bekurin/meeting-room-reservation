package meeting.room.reservation.service

import meeting.room.reservation.controller.request.ReservationV2CreationRequest
import meeting.room.reservation.domain.Reservation
import meeting.room.reservation.domain.ReservationParticipant
import meeting.room.reservation.domain.Room
import meeting.room.reservation.domain.User
import meeting.room.reservation.exception.BadRequestException
import meeting.room.reservation.exception.ResourceNotFoundException
import java.time.Duration
import java.time.LocalDateTime

data class ReservationCreationContext(
    val users: List<User>,
    val room: Room,
    val overlappedReservation: List<Reservation>,
    val request: ReservationV2CreationRequest,
) {
    fun validate() {
        if (overlappedReservation.isNotEmpty()) {
            throw BadRequestException("회의실이 이미 예약되어 있습니다.")
        }
        if (room.capacity < users.size) {
            throw BadRequestException("회의 참여 인원이 너무 많습니다")
        }
        if (room.available.not()) {
            throw BadRequestException("현재 회의실을 사용할 수 없습니다.")
        }

        if (request.startAt.isAfter(request.endAt)) {
            throw BadRequestException("")
        }

        val betweenMinutes = Duration.between(request.startAt, request.endAt)
            .toMinutes()

        if (betweenMinutes < 10 || betweenMinutes > 480) {
            throw BadRequestException("")
        }
    }

    fun getCreator(): User {
        return users.firstOrNull { user -> user.id == request.creatorId }
            ?: throw ResourceNotFoundException("회원 정보를 찾을 수 없습니다.")
    }

    fun getStartAt(): LocalDateTime {
        return request.startAt
    }

    fun getEndAt(): LocalDateTime {
        return request.endAt
    }

    fun createReservationParticipant(savedReservation: Reservation): List<ReservationParticipant> {
        return getParticipants().map { participant ->
            ReservationParticipant(reservation = savedReservation, participant)
        }
    }

    private fun getParticipants(): List<User> {
        return users.filter { user -> request.participants.contains(user.id) }
    }
}
