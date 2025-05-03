package garlic.burger.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "nutrition")
class Nutrition(
    product: Product,
    weight: Int,
    calories: Int,
    sugars: Int,
    protein: Int,
    totalFat: Int,
    sodium: Int,
    caffeine: Int,
) : BaseEntity() {
    @Column
    var productId: Int = product.id
        protected set

    @Column
    var weight: Int = weight
        protected set

    @Column
    var calories: Int = calories
        protected set

    @Column
    var sugars: Int = sugars
        protected set

    @Column
    var protein: Int = protein
        protected set

    @Column
    var totalFat: Int = totalFat
        protected set

    @Column
    var sodium: Int = sodium
        protected set

    @Column
    var caffeine: Int = caffeine
        protected set
}
