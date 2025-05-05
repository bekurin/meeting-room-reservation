package meeting.room.reservation.service

import meeting.room.reservation.controller.request.ReservationV2CreationRequest
import meeting.room.reservation.controller.response.ReservationResponse
import meeting.room.reservation.domain.Reservation
import meeting.room.reservation.exception.ResourceNotFoundException
import meeting.room.reservation.repository.ReservationParticipantRepository
import meeting.room.reservation.repository.ReservationRepository
import meeting.room.reservation.repository.RoomRepository
import meeting.room.reservation.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReservationV2Service(
    private val reservationRepository: ReservationRepository,
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository,
    private val reservationParticipantRepository: ReservationParticipantRepository,
) {
    fun createReservation(request: ReservationV2CreationRequest): ReservationResponse {
        val room = roomRepository.findById(request.roomId)
            .orElseThrow { throw ResourceNotFoundException("회의실 정보를 찾을 수 없습니다.") }
        val users = userRepository.findByIdIn(request.getUserIds().toList())
        val overlappedReservations = reservationRepository.findByRoomIdAndStartAtLessThanAndEndAtGreaterThan(request.roomId, request.startAt, request.endAt)

        val reservationCreationContext = ReservationCreationContext(users, room, overlappedReservations, request)
        val createdReservation = Reservation(reservationCreationContext)
        val savedReservation = reservationRepository.save(createdReservation)

        val reservationParticipants = reservationCreationContext.createReservationParticipant(savedReservation)
        val savedReservationParticipants = reservationParticipantRepository.saveAll(reservationParticipants)
        return ReservationResponse(savedReservation, savedReservationParticipants)
    }
}
