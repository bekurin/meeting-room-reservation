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
    title: String,
    room: Room,
    creator: User,
    startAt: LocalDateTime,
    endAt: LocalDateTime,
) : BaseEntity() {
    /**
     * 추후에는 기본 생성자를 ReservationCreationContext 만 가능하도록 구현한다.
     * 그러면 소스 코드 어디에서도 ReesrvationCreationContext를 만들어서 Reservation 객체를 만들도록 강제할 수 있다.
     * 그렇다면 항상 동일한 검증을 수행하도록 유도할 수 있다.
     */
    constructor(reservationCreationContext: ReservationCreationContext) : this(
        title = reservationCreationContext.request.title,
        room = reservationCreationContext.room,
        creator = reservationCreationContext.getCreator(),
        startAt = reservationCreationContext.getStartAt(),
        endAt = reservationCreationContext.getEndAt(),
    ) {
        reservationCreationContext.validate()
    }

    @Column(nullable = false)
    var title: String = title
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
}
