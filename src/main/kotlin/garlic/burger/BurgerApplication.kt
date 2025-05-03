package garlic.burger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BurgerApplication

fun main(args: Array<String>) {
	runApplication<BurgerApplication>(*args)
}
