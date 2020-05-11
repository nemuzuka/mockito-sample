package net.jp.vss.sample.usecase.tasks

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * TaskUseCaseResult のテスト.
 */
class TaskUseCaseResultTest {

    companion object {
        val objectMapper = ObjectMapper()
    }

    @Test
    fun testJsonString() {
        // setup
        val sut = TaskUseCaseResultFixtures.create()

        // execution
        val actual = objectMapper.writeValueAsString(sut)

        // verify
        val expected = """
            |{
                |"task_code":"${sut.taskCode}",
                |"status":"${sut.status.name}",
                |"title":"${sut.title}",
                |"content":"${sut.content}",
                |"deadline":${sut.deadline}
            |}
        """.trimMargin().replace("\n", "")
        assertThat(actual).isEqualTo(expected)
    }

    /**
     * null を含むプロパティの JSON 化.
     */
    @Test
    fun testJsonString_NullValue() {
        // setup
        val sut = TaskUseCaseResultFixtures.create()
            .copy(deadline = null)

        // execution
        val actual = objectMapper.writeValueAsString(sut)

        // verify
        val expected = """
            |{
                |"task_code":"${sut.taskCode}",
                |"status":"${sut.status.name}",
                |"title":"${sut.title}",
                |"content":"${sut.content}",
                |"deadline":null
            |}
        """.trimMargin().replace("\n", "")
        assertThat(actual).isEqualTo(expected)
    }
}
