package garlic.burger.configuration

import garlic.burger.domain.Events
import jakarta.annotation.PostConstruct
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationEventConfiguration(
    private val applicationContext: ApplicationContext
) {
    @PostConstruct
    fun init() {
        Events.setPublisher(applicationContext)
    }
}
