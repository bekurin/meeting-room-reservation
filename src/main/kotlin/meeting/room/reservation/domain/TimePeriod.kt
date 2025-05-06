package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import meeting.room.reservation.exception.BadRequestException
import java.time.Duration
import java.time.LocalDateTime

@Embeddable
data class TimePeriod(
    @Column(nullable = false)
    val startAt: LocalDateTime,
    @Column(nullable = false)
    val endAt: LocalDateTime,
) {
    init {
        if (startAt.isAfter(endAt)) {
            throw BadRequestException("")
        }

        val betweenMinutes = Duration.between(startAt, endAt).toMinutes()
        if (betweenMinutes < 10 || betweenMinutes > 480) {
            throw BadRequestException("")
        }
    }
}
