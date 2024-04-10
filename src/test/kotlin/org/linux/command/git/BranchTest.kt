package org.linux.command.git

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.linux.command.org.linux.command.git.Branch

class BranchTest : FunSpec({
    test("wiii") {
        val branch = Branch.of(wiii)
        branch shouldBe Branch(
            current = "DSS-4861/oracle9",
            localBranches = listOf("DSS-4861/oracle9", "develop"),
            remotes = mapOf(
                "myRepo" to listOf("DSS-4861/oracle9"),
                "origin" to listOf("develop")
            )
        )

    }
})

val wiii = """
* DSS-4861/oracle9
  develop
  remotes/myRepo/DSS-4861/oracle9
  remotes/origin/HEAD -> origin/develop
  remotes/origin/develop
""".trimIndent()
