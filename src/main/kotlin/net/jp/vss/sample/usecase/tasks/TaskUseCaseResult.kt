package net.jp.vss.sample.usecase.tasks

import com.fasterxml.jackson.annotation.JsonProperty
import net.jp.vss.sample.domain.tasks.Task

/**
 * TaskUseCase の結果.
 *
 * JSON 化することを想定しています。
 * @property taskCode タスクコード
 * @property status ステータス
 * @property title 件名
 * @property content 内容
 * @property deadline 期日
 * @property attributes 付帯情報
 * @property resourceAttributesResult リソース付帯情報
 */
data class TaskUseCaseResult(
    @field:JsonProperty("task_code")
    val taskCode: String,

    @field:JsonProperty("status")
    val status: Task.TaskStatus,

    @field:JsonProperty("title")
    val title: String,

    @field:JsonProperty("content")
    val content: String,

    @field:JsonProperty("deadline")
    val deadline: Long?
) {
    companion object {
        /**
         * Task からのインスタンス生成.
         *
         * @param task 対象 Task
         * @return 生成インスタンス
         */
        fun of(task: Task): TaskUseCaseResult {
            val taskDetail = task.taskDetail
            return TaskUseCaseResult(
                taskCode = task.taskCode.value,
                status = task.status,
                title = taskDetail.title,
                content = taskDetail.content,
                deadline = taskDetail.deadline)
        }
    }
}
