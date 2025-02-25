package org.linux.command.git

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.linux.command.org.linux.command.git.Branch

class BranchTest : FunSpec({
    test("parse") {
        Branch.parser(gitBranchOutput) shouldBe Branch(
            current = "DSS-4861/oracle9",
            locals = setOf("DSS-4861/oracle9", "develop"),
            remotes = mapOf(
                "myRepo" to setOf("DSS-4861/oracle9"),
                "origin" to setOf("develop")
            )
        )
    }

    test("example") {
        Branch.done()
    }
})

val gitBranchOutput = """
* DSS-4861/oracle9
  develop
  remotes/myRepo/DSS-4861/oracle9
  remotes/origin/HEAD -> origin/develop
  remotes/origin/develop
""".trimIndent()
