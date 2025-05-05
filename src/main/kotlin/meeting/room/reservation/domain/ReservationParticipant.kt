package meeting.room.reservation.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "reservation_participant")
class ReservationParticipant(
    reservation: Reservation,
    user: User,
) : BaseEntity() {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reservation_id")
    var reservation: Reservation = reservation
        protected set

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set
}
