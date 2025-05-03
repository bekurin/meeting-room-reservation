package garlic.burger.infrastructure

import garlic.burger.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Int> {
}
