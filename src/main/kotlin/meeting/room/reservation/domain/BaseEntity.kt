package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity : TimestampBaseEntity() {
    @Id
    @Column(nullable = false, length = 11, columnDefinition = "int(11)", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
