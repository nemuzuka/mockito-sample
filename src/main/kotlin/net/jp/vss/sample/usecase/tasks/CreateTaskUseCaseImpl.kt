package net.jp.vss.sample.usecase.tasks

import net.jp.vss.sample.domain.tasks.Task
import org.springframework.stereotype.Service

/**
 * Implements {@link CreateTaskUseCase}.
 *
 * 本来なら Repository とかを使用して永続化します。
 */
@Service
class CreateTaskUseCaseImpl : CreateTaskUseCase {

    override fun createTask(parameter: CreateTaskUseCaseParameter): TaskUseCaseResult {
        // TODO create task の実装
        val task = Task.buildForCreate("task-001", "ダミー title", "ダミー内容", null)
        return TaskUseCaseResult.of(task)
    }
}
