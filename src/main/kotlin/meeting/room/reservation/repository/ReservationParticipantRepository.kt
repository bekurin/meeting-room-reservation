package meeting.room.reservation.repository

import meeting.room.reservation.domain.ReservationParticipant
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationParticipantRepository : JpaRepository<ReservationParticipant, Int>
