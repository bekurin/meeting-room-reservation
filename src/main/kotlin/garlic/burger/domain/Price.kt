package garlic.burger.domain

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class Price(
    val price: BigDecimal,
)
