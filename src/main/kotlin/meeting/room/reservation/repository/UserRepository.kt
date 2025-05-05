package meeting.room.reservation.repository

import meeting.room.reservation.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByIdIn(userIds: List<Int>): List<User>
}
