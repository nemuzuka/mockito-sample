package net.jp.vss.sample.usecase.tasks

/**
 * CreateTaskUseCase のパラメータ.
 *
 * @property taskCode タスクコード
 * @property title 件名
 * @property content 内容
 * @property deadline 期日
 */
data class CreateTaskUseCaseParameter(
    val taskCode: String,
    val title: String,
    val content: String,
    val deadline: Long?
)
