package garlic.burger.repository

import garlic.burger.domain.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository: JpaRepository<Menu, Int> {
}
