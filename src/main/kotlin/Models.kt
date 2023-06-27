data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
) {

    fun prettyPrint(): String {
        return "$`val`${left?.prettyPrint()?.let { " ,$it" }?:""}${right?.prettyPrint()?.let { " ,$it" }?:""}"
    }
}