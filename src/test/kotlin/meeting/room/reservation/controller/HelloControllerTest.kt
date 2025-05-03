package meeting.room.reservation.controller

import com.fasterxml.jackson.module.kotlin.readValue
import meeting.room.reservation.configuration.IntegrationTestBase
import meeting.room.reservation.controller.response.HelloResponse
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

class HelloControllerTest : IntegrationTestBase() {
    @Test
    fun `hello API를 호출하면 파라미터로 넘긴 username이 출력됩니다`() {
        // given
        val username = UUID.randomUUID().toString()
        val expectedResponse = HelloResponse(username)

        // when
        val performed = mockMvc.perform(
            get("/hello")
                .param("username", username)
                .contentType(MediaType.APPLICATION_JSON),
        )

        // then
        val response = objectMapper.readValue<HelloResponse>(performed.andReturn().response.contentAsString)
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(performed.andExpect(status().isOk))
            softly.assertThat(response).isEqualTo(expectedResponse)
        }
    }
}

