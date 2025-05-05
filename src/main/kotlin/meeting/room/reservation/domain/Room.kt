package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Room(
    name: String,
    location: String,
    capacity: Int,
    available: Boolean,
) : BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var location: String = location
        protected set

    @Column(nullable = false)
    var capacity: Int = capacity
        protected set

    @Column(nullable = false)
    var available: Boolean = available
        protected set
}
