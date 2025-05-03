package garlic.burger.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.testcontainers.junit.jupiter.Testcontainers

@WebMvcTest
@SpringBootTest
@Testcontainers
@ExtendWith(RepositoryContainerExtension::class)
open class IntegrationTestBase {
    @Autowired
    protected lateinit var entityManager: EntityManager

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var transactionManager: PlatformTransactionManager

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    protected fun <T> transactional(action: () -> T): T {
        val status = openTransaction()
        val result = action()
        closeTransaction(status)
        return result
    }

    private fun openTransaction(): TransactionStatus {
        return transactionManager.getTransaction(DefaultTransactionDefinition())
    }

    private fun closeTransaction(transactionStatus: TransactionStatus) {
        transactionManager.commit(transactionStatus)
    }
}
