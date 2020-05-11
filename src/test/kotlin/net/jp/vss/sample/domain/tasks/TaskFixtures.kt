package net.jp.vss.sample.domain.tasks

/**
 * Task の Fixture.
 */
class TaskFixtures {
    companion object {
        fun create() = create("TASK-0001")

        fun create(taskCodeValue: String): Task {
            val taskCode = Task.TaskCode(taskCodeValue)
            val taskDetail = Task.TaskDetail(title = "TASK-0001の件名",
                content = "頑張ってテストを書く",
                deadline = 1546732800000L)
            return Task(taskCode = taskCode, status = Task.TaskStatus.OPEN,
                taskDetail = taskDetail)
        }
    }
}
