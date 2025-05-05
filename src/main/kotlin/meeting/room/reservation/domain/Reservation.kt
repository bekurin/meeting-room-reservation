package meeting.room.reservation.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import meeting.room.reservation.service.ReservationCreationContext
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
class Reservation(
    room: Room,
    creator: User,
    startAt: LocalDateTime,
    endAt: LocalDateTime,
) : BaseEntity() {
    constructor(reservationCreationContext: ReservationCreationContext) : this(
        room = reservationCreationContext.room,
        creator = reservationCreationContext.getCreator(),
        startAt = reservationCreationContext.getStartAt(),
        endAt = reservationCreationContext.getEndAt(),
    ) {
        reservationCreationContext.validate()
    }

    @Column(nullable = false)
    var title: String = ""
        protected set

    @Column(nullable = false)
    var startAt: LocalDateTime = startAt
        protected set

    @Column(nullable = false)
    var endAt: LocalDateTime = endAt
        protected set

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    var room: Room = room
        protected set

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "creator_id")
    var creator: User = creator
        protected set
//
//    @OneToMany(mappedBy = "reservation")
//    val participants: MutableList<ReservationParticipant> = mutableListOf()
}
