package garlic.burger.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "category")
class Category(
    name: String,
): BaseEntity() {
    @Column
    var name: String = name
        protected set
}
