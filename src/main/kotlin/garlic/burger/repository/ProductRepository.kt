package garlic.burger.repository

import garlic.burger.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Int> {
}
