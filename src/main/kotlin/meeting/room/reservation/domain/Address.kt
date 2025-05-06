package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import meeting.room.reservation.exception.BadRequestException

@Embeddable
data class Address(
    @Column(nullable = false)
    val city: String,
    @Column(nullable = false)
    val street: String,
    @Column(nullable = false)
    val zipcode: String,
) {
    init {
        if (city.isBlank() || street.isBlank() || zipcode.isBlank()) {
            throw BadRequestException("")
        }
    }
}
