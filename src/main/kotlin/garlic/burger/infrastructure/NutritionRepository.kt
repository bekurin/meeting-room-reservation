package garlic.burger.infrastructure

import garlic.burger.domain.Nutrition
import org.springframework.data.jpa.repository.JpaRepository

interface NutritionRepository: JpaRepository<Nutrition, Int> {
}
