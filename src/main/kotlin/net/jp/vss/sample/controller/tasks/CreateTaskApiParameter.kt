package net.jp.vss.sample.controller.tasks

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import net.jp.vss.sample.usecase.tasks.CreateTaskUseCaseParameter

/**
 * CreateTaskController のパラメータ.
 */
data class CreateTaskApiParameter(

    @field:NotNull
    @field:Pattern(regexp = "[a-zA-Z0-9][-a-zA-Z0-9_]{0,127}")
    @field:Size(max = 128)
    @field:JsonProperty("task_code")
    val taskCode: String? = null,

    @field:NotNull
    @field:Size(max = 256)
    @field:JsonProperty("title")
    val title: String? = null,

    @field:NotNull
    @field:JsonProperty("content")
    val content: String? = null,

    @field:JsonProperty("deadline")
    val deadline: Long? = null
) {
    /**
     * CreateTaskUseCaseParameter に変換.
     *
     * @param createUserCode 登録ユーザコード
     * @return 生成 CreateTaskUseCaseParameter
     */
    fun toParameter() = CreateTaskUseCaseParameter(
        taskCode = taskCode!!,
        title = title!!,
        content = content!!,
        deadline = deadline!!)
}
