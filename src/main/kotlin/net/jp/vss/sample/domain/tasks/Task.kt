package net.jp.vss.sample.domain.tasks

/**
 * タスク.
 *
 * @property taskCode タスクコード
 * @property status ステータス
 * @property taskDetail タスク詳細
 */
data class Task(
    val taskCode: TaskCode,
    val status: TaskStatus,
    val taskDetail: TaskDetail
) {

    companion object {
        /**
         * 登録時のインスタンス生成.
         *
         * @param taskCodeValue タスクコード文字列
         * @param title 件名
         * @param content 内容
         * @param deadline 期日
         * @return 登録時のTask
         */
        fun buildForCreate(
            taskCodeValue: String,
            title: String,
            content: String,
            deadline: Long?
        ): Task {
            val taskCode = TaskCode(taskCodeValue)
            val taskDetail = TaskDetail(title = title,
                    content = content,
                    deadline = deadline)
            return Task(taskCode = taskCode, status = TaskStatus.OPEN, taskDetail = taskDetail)
        }
    }

    /**
     * 処理済み.
     *
     * @return 処理済みの Task
     */
    fun done() = this.copy(status = TaskStatus.DONE)

    /**
     * 再オープン.
     *
     * @param updateUserCode 更新ユーザコード
     * @return 再オープンした Task
     */
    fun reopen() = this.copy(status = TaskStatus.OPEN)

    /**
     * タスクコード値オブジェクト.
     *
     * @property value
     */
    data class TaskCode(val value: String)

    /**
     * タスク詳細値オブジェクト.
     *
     * @property title 件名
     * @property content 内容
     * @property deadline 期日
     */
    data class TaskDetail(
        val title: String,
        val content: String,
        val deadline: Long?
    )

    /**
     * Task のステータス.
     */
    enum class TaskStatus {
        /** Open. */
        OPEN,
        /** 処理済み. */
        DONE
    }
}
