package garlic.burger.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "menu")
class Menu(
    product: Product,
    category: Category,
): BaseEntity() {
    @Column(nullable = false)
    var productId: Int = product.id
        protected set

    @Column(nullable = false)
    var categoryId: Int = category.id
        protected set
}
