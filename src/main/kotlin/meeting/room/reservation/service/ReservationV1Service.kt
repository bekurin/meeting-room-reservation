package meeting.room.reservation.service

import meeting.room.reservation.controller.request.ReservationV1CreationRequest
import meeting.room.reservation.controller.response.ReservationResponse
import meeting.room.reservation.domain.ReservationParticipant
import meeting.room.reservation.exception.BadRequestException
import meeting.room.reservation.exception.ResourceNotFoundException
import meeting.room.reservation.repository.ReservationParticipantRepository
import meeting.room.reservation.repository.ReservationRepository
import meeting.room.reservation.repository.RoomRepository
import meeting.room.reservation.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
@Transactional(readOnly = true)
class ReservationV1Service(
    private val reservationRepository: ReservationRepository,
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository,
    private val reservationParticipantRepository: ReservationParticipantRepository,
) {
    fun createReservation(request: ReservationV1CreationRequest): ReservationResponse {
        // 1. 예약 시작/종료 시간 검증
        if (request.startAt.isAfter(request.endAt)) {
            throw BadRequestException("예약 시작 시간은 예약 종료 시간보다 미래일 수 없습니다.")
        }

        // 2. 예약 시간 검증
        val durationMinutes = Duration.between(request.startAt, request.endAt).toMinutes()
        if (durationMinutes < 10 || durationMinutes >= 480) {
            throw BadRequestException("예약 시간은 최소 10분부터 최대 8시간 사이여야 합니다.")
        }

        // 3. 회의실 존재 여부 및 사용 가능 검증
        val room = roomRepository.findById(request.roomId)
                .orElseThrow { throw ResourceNotFoundException("회의실 정보를 찾을 수 없습니다.") }
        if (room.available.not()) {
            throw BadRequestException("현재 회의실이 사용 불가 상태입니다.")
        }

        val userIds = request.participants + request.creatorId
        val users = userRepository.findByIdIn(userIds)
        val userIdToUser = users.associateBy { it.id }

        // 4. 회원 존재 여부 검증
        userIds.forEach { userId ->
            userIdToUser[userId] ?: throw ResourceNotFoundException("회원 정보를 찾을 수 없습니다")
        }

        // 5. 회의실 최대 인원 검증
        if (room.capacity < users.size) {
            throw BadRequestException("회의 참여 인원이 너무 많습니다")
        }

        // 6. 중복 회의실 검증 및 gap 락 획득
        val overlappedReservations = reservationRepository.findByRoomIdAndStartAtLessThanAndEndAtGreaterThan(
                request.roomId,
                request.startAt,
                request.endAt,
            )
        if (overlappedReservations.isNotEmpty()) {
            throw BadRequestException("회의실이 이미 예약되어 있습니다.")
        }

        // 7. 회의실 저장
        val creator = userIdToUser[request.creatorId] ?: throw ResourceNotFoundException("회원 정보를 찾을 수 없습니다.")
        val reservation = request.toReservation(creator, room)
        val savedReservation = reservationRepository.save(reservation)

        // 8. 회의실 참여자 저장
        val reservationParticipants = request.participants.map { participantId ->
                val participant = userIdToUser[participantId] ?: throw ResourceNotFoundException("회원 정보를 찾을 수 없습니다")
                ReservationParticipant(savedReservation, participant)
            }
        val savedReservationParticipants = reservationParticipantRepository.saveAll(reservationParticipants)

        return ReservationResponse(savedReservation, savedReservationParticipants)
    }
}
