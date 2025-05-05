package meeting.room.reservation.repository

import jakarta.persistence.LockModeType
import meeting.room.reservation.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.time.LocalDateTime

interface ReservationRepository : JpaRepository<Reservation, Int> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByRoomIdAndStartAtLessThanAndEndAtGreaterThan(
        roomId: Int,
        endAt: LocalDateTime,
        startAt: LocalDateTime,
    ): List<Reservation>
}
