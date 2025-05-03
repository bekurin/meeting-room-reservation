package garlic.burger.domain

import org.springframework.context.ApplicationEventPublisher

class Events {
    companion object {
        private lateinit var publisher: ApplicationEventPublisher

        fun raise(event: Any) {
            publisher.publishEvent(event)
        }

        fun setPublisher(publisher: ApplicationEventPublisher) {
            Companion.publisher = publisher
        }
    }
}
