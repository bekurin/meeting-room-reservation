package garlic.burger.domain

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class Product(
    name: String,
    price: Price,
    description: String,
    nutrition: Nutrition,
): BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Embedded
    @Column(nullable = false)
    var price: Price = price
        protected set

    @Column(nullable = false)
    var description: String = description
        protected set

    @Column(nullable = false)
    var nutritionId: Int = nutrition.id
        protected set
}
