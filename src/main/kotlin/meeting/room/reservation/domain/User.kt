package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User(
    name: String,
    email: String,
) : BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var email: String = email
        protected set
}
