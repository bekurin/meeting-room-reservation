package meeting.room.reservation.service

import meeting.room.reservation.controller.request.ReservationV1CreationRequest
import meeting.room.reservation.repository.ReservationParticipantRepository
import meeting.room.reservation.repository.ReservationRepository
import meeting.room.reservation.repository.RoomRepository
import meeting.room.reservation.repository.UserRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class ReservationV1ServiceTest {

    @InjectMocks
    private lateinit var reservationV1Service: ReservationV1Service

    @Mock
    private lateinit var reservationRepository: ReservationRepository

    @Mock
    private lateinit var roomRepository: RoomRepository

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var reservationParticipantRepository: ReservationParticipantRepository

    @Nested
    inner class `회의실 예약을 생성할 때` {
        @Test
        fun `예약 시작 시간은 예약 종료 시간보다 미래일 수 없다`() {
            // given
            val request = createReservationCreationRequest()

            // when
            val reservationResponse = reservationV1Service.createReservation(request)

            // then
        }

        @Test
        fun `예약 시간은 최소 10분부터 최대 8시간 사이여야 한다`() {

        }

        @Test
        fun `회의실이 사용 가능한 상태여야 한다`() {
        }

        @Test
        fun `회의실이 존재해야한다`() {

        }

        @Test
        fun `회의실의 최대 수요 인원을 넘을 수 없다`() {

        }

        @Test
        fun `중복된 시간에 회의실 예약이 있으면 안된다`() {

        }

        @DisplayName(
            """
            - 예약 시작 시간은 예약 종료 시간보다 미래일 수 없다
            - 예약 시간은 최소 10분부터 최대 8시간 사이여야 한다
            - 회의실이 사용 가능한 상태여야 한다
            - 회의실이 존재해야한다
            - 회의실의 최대 수요 인원을 넘을 수 없다
            - 중복된 시간에 회의실 예약이 있으면 안된다
            위 내용을 모두 만족하면 예약 생성에 성공한다.

        """
        )
        @Test
        fun `예약 생성에 성공한다`() {

        }

        private fun createReservationCreationRequest(
            title: String = "",
            startAt: LocalDateTime = LocalDateTime.now(),
            endAt: LocalDateTime = LocalDateTime.now().plusDays(1),
            creatorId: Int = 1,
            roomId: Int = 1,
            participants: List<Int> = listOf(),
        ): ReservationV1CreationRequest {
            return ReservationV1CreationRequest(
                title = title,
                startAt = startAt,
                endAt = endAt,
                creatorId = creatorId,
                roomId = roomId,
                participants = participants
            )
        }
    }

}