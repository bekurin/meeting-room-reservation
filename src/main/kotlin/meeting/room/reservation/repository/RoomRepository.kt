package meeting.room.reservation.repository

import meeting.room.reservation.domain.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository : JpaRepository<Room, Int>
