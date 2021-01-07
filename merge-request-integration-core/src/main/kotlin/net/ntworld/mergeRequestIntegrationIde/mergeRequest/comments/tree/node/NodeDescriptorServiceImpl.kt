package net.ntworld.mergeRequestIntegrationIde.mergeRequest.comments.tree.node

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import net.ntworld.mergeRequest.ProviderData
import net.ntworld.mergeRequestIntegrationIde.infrastructure.ProjectServiceProvider

class NodeDescriptorServiceImpl(
    private val projectServiceProvider: ProjectServiceProvider,
    private val providerData: ProviderData
) : NodeDescriptorService {

    override fun make(node: Node): PresentableNodeDescriptor<Node> {
        val presentation = MyPresentableNodeDescriptor(projectServiceProvider, providerData, node)
        presentation.update()
        return presentation
    }

    override fun findNode(input: Any?): Node? {
        return if (null !== input && input is MyPresentableNodeDescriptor) {
            input.element
        } else null
    }

    private class MyPresentableNodeDescriptor(
        private val projectServiceProvider: ProjectServiceProvider,
        private val providerData: ProviderData,
        private val element: Node
    ) : PresentableNodeDescriptor<Node>(projectServiceProvider.project, null) {
        override fun update(presentation: PresentationData) {
            element.updatePresentation(projectServiceProvider, providerData, presentation)
        }

        override fun getElement(): Node = element
    }

}