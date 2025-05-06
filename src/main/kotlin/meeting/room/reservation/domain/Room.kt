package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity

@Entity
class Room(
    name: String,
    address: Address,
    capacity: Int,
    available: Boolean,
) : BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Embedded
    var address: Address = address
        protected set

    @Column(nullable = false)
    var capacity: Int = capacity
        protected set

    @Column(nullable = false)
    var available: Boolean = available
        protected set
}
