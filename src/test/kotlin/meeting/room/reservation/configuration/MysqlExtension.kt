package meeting.room.reservation.configuration

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.lifecycle.Startables

class MysqlExtension : BeforeAllCallback {
    companion object {
        private const val MYSQL_VERSION = "mysql:8.0.28"
        private const val DATABASE_NAME = "test"
        private const val USERNAME = "test"
        private const val PASSWORD = "password"
        private const val MYSQL_PORT = 3306

        @Container
        val mysqlContainer: MySQLContainer<*> = MySQLContainer(MYSQL_VERSION)
            .withCommand(
                "--character-set-server=utf8mb4",
                "--collation-server=utf8mb4_unicode_ci",
                "--max_connections=1000",
            )
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withExposedPorts(MYSQL_PORT)
    }

    override fun beforeAll(context: ExtensionContext?) {
        if (mysqlContainer.isRunning.not()) {
            return
        }
        Startables.deepStart(mysqlContainer).join()

        System.setProperty(
            "spring.datasource.url",
            "jdbc:mysql://localhost:${mysqlContainer.getMappedPort(MYSQL_PORT)}/${DATABASE_NAME}?useSSL=false&useUnicode=true&characterEncoding=UTF-8&useFractionalSeconds=false"
        )
        System.setProperty("spring.datasource.username", USERNAME)
        System.setProperty("spring.datasource.password", PASSWORD)
    }
}
