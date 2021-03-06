package net.jp.vss.sample.controller.tasks

import net.jp.vss.sample.controller.exceptions.HttpConflictException
import net.jp.vss.sample.domain.exceptions.DuplicateException
import net.jp.vss.sample.usecase.tasks.CreateTaskUseCase
import net.jp.vss.sample.usecase.tasks.TaskUseCaseResult
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * CreateTask の APIController.
 *
 * @property createTaskUseCase CreateTask の UseCase
 */
@RestController
@RequestMapping("/api/tasks")
@Validated
class CreateTaskApiController(
    private val createTaskUseCase: CreateTaskUseCase
) {

    companion object {
        private val log = LoggerFactory.getLogger(CreateTaskApiController::class.java)
    }

    /**
     * CreateTask.
     *
     * @param parameter パラメータ
     * @return レスポンス
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTask(
        @Validated
        @RequestBody
        parameter: CreateTaskApiParameter
    ): ResponseEntity<TaskUseCaseResult> {

        try {
            val result = createTaskUseCase.createTask(parameter.toParameter())
            return ResponseEntity.ok(result)
        } catch (e: DuplicateException) {
            // 既に task_code が存在した場合
            log.info("Conclift Parameter({}) {}", parameter, e.message)
            throw HttpConflictException(e.message!!, e)
        }
    }
}
