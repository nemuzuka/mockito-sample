package net.jp.vss.sample.controller.tasks

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import net.jp.vss.sample.domain.exceptions.DuplicateException
import net.jp.vss.sample.usecase.tasks.CreateTaskUseCase
import net.jp.vss.sample.usecase.tasks.CreateTaskUseCaseParameter
import net.jp.vss.sample.usecase.tasks.TaskUseCaseResultFixtures
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * CreateTaskApiController のテスト.
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class CreateTaskApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var createTaskUseCase: CreateTaskUseCase

    @Test
    fun testCreateTask() {
        // setup
        val taskUseCaseResult = TaskUseCaseResultFixtures.create()
        whenever(createTaskUseCase.createTask(any())).thenReturn(taskUseCaseResult)

        val parameter = CreateTaskApiParameterFixtures.create()
        val mapper = ObjectMapper()
        val content = mapper.writeValueAsString(parameter)

        // execution
        mockMvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            // verify
            .andExpect(status().isOk)
            .andExpect(jsonPath("task_code").value(`is`(taskUseCaseResult.taskCode)))

        // mock のパラメータに対する verify
        argumentCaptor<CreateTaskUseCaseParameter>().apply {
            verify(createTaskUseCase).createTask(capture())
            val capturedParameter = firstValue
            val expectedParameter = parameter.toParameter()
            assertThat(capturedParameter).isEqualTo(expectedParameter)
        }
    }

    @Test
    fun testCreateTask_ConflictTaskCode_409() {
        // setup
        val exception = DuplicateException("dummy")
        whenever(createTaskUseCase.createTask(any())).thenThrow(exception)

        val parameter = CreateTaskApiParameterFixtures.create()
        val mapper = ObjectMapper()
        val content = mapper.writeValueAsString(parameter)

        // execution
        mockMvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
            // verify
            .andExpect(status().isConflict)
    }
}
