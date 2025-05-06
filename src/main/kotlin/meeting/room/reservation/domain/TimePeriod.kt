package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
data class TimePeriod(
    @Column(nullable = false)
    val startAt: LocalDateTime,
    @Column(nullable = false)
    val endAt: LocalDateTime,
)
