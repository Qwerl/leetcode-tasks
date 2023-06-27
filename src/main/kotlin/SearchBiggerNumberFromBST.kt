import java.util.*

class SearchBiggerNumberFromBST {

    class Solution {

        fun searchN(bst: TreeNode?, target: Int, targetElementsCount: Int): List<Int> {
            val stack = LinkedList<TreeNode>()
            var currentRoot = bst
            val result = mutableListOf<Int>()
            while (currentRoot != null || stack.isNotEmpty()) {
                while (currentRoot != null) {
                    stack.push(currentRoot)
                    val left = currentRoot.left
                    if (currentRoot.canHaveTargetLeft(targetElementsCount)) break
                    else currentRoot = left
                }
                currentRoot = stack.pop()
                if (currentRoot.`val` >= target) result.add(currentRoot.`val`)
                if (result.size == targetElementsCount) break
                if (currentRoot.canHaveTargetRight(targetElementsCount) && result.size == targetElementsCount) break
                val right = currentRoot.right
                currentRoot = right
            }
            return result.toList()
        }

        private fun TreeNode?.canHaveTargetRight(target: Int) =
            this?.let { it.`val` > target } == true && right?.let { it.`val` > target } == true

        private fun TreeNode.canHaveTargetLeft(target: Int) =
            this.`val` < target && left?.let { it.`val` < target } == true

        fun searchLOGN(bst: TreeNode?, target: Int, targetElements: Int): List<Int> {
            if (bst == null || targetElements <= 0) return emptyList()
            val result = mutableListOf<Int>()
            if (bst.`val` >= target) {
                result.add(bst.`val`)
                if (result.size == targetElements) return result
            }
            bst.left?.let { result.addAll(searchLOGN(it, target, targetElements - result.size)) }
            bst.right?.let { result.addAll(searchLOGN(it, target, targetElements - result.size)) }
            return result
        }

    }
}

fun main() {
    val bst = TreeNode(
        10,
        left = TreeNode(
            5,
            TreeNode(3),
            TreeNode(
                7,
                TreeNode(6),
                null
            )
        ),
        right = TreeNode(
            15,
            null,
            TreeNode(18)
        ),
    )
    val result = SearchBiggerNumberFromBST.Solution().searchN(bst, 7, 3)
    if (!result.containsAll(listOf(7, 10, 15))) println("FAIL: $result")
    else println("success, result is $result")

    val result2 = SearchBiggerNumberFromBST.Solution().searchN(bst, 7, 4)
    if (!result2.containsAll(listOf(7, 10, 15, 18))) println("FAIL: $result2")
    else println("success, result is $result2")

    val result3 = SearchBiggerNumberFromBST.Solution().searchN(bst, 5, 5)
    if (!result3.containsAll(listOf(5, 6, 7, 10, 15))) println("FAIL: $result3")
    else println("success, result is $result3")
}