package net.ntworld.mergeRequestIntegrationIde.mergeRequest.comments

import com.intellij.openapi.Disposable
import com.intellij.ui.tabs.TabInfo
import net.ntworld.mergeRequest.Comment
import net.ntworld.mergeRequest.CommentPosition
import net.ntworld.mergeRequest.MergeRequestInfo
import net.ntworld.mergeRequestIntegrationIde.Component
import net.ntworld.mergeRequestIntegrationIde.View
import net.ntworld.mergeRequestIntegrationIde.component.comment.CommentEvent
import net.ntworld.mergeRequestIntegrationIde.mergeRequest.comments.tree.node.Node
import java.util.*

interface CommentsTabView : View<CommentsTabView.ActionListener>, Component, Disposable {
    val tabInfo: TabInfo

    fun displayCommentCount(count: Int)

    fun renderTree(mergeRequestInfo: MergeRequestInfo, comments: List<Comment>, displayResolvedComments: Boolean)

    fun hideThread()

    fun renderThread(mergeRequestInfo: MergeRequestInfo, groupedComments: Map<String, List<Comment>>)

    fun hasGeneralCommentsTreeNode(): Boolean

    fun selectGeneralCommentsTreeNode()

    fun focusToMainEditor()

    fun clearMainEditorText()

    interface ActionListener : EventListener, CommentEvent {
        fun onTreeNodeSelected(node: Node)

        fun onShowResolvedCommentsToggled(displayResolvedComments: Boolean)

        fun onCreateGeneralCommentClicked()

        fun onRefreshButtonClicked()

        fun onReplyCommentRequested(repliedComment: Comment, content: String)

        fun onCreateCommentRequested(content: String, position: CommentPosition?, isDraft: Boolean)
    }
}