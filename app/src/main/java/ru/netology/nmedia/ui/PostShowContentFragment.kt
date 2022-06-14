package ru.netology.nmedia.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.getTextViewCount
import ru.netology.nmedia.databinding.PostShowContentDetailFragmentBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.postViewModel.PostViewModel


class PostShowContentFragment : Fragment() {

    private val args by navArgs<PostShowContentFragmentArgs>()
    private val viewModel by viewModels<PostViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.sharePostContent.observe(this) { postContent ->
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, postContent)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
            startActivity(shareIntent)
        }

        viewModel.videoLinkPlay.observe(this) { videoLink ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                val uri = Uri.parse(videoLink)
                data = uri
            }
            val openVideoIntent =
                Intent.createChooser(intent, getString(R.string.chooser_play_video))
            startActivity(openVideoIntent)
        }

        setFragmentResultListener(requestKey = PostContentFragment.REQUEST_KEY) { requestKey, bundle ->
            if (requestKey != PostContentFragment.REQUEST_KEY) return@setFragmentResultListener
            val newPostContent =
                bundle.getString(PostContentFragment.RESULT_KEY) ?: return@setFragmentResultListener
            viewModel.onSaveButtonClicked(newPostContent)
        }

        viewModel.navigateToPostContentScreenEvent.observe(this) { initialContent ->
            val direction = PostShowContentFragmentDirections.toPostContentFragment(initialContent)
            findNavController().navigate(direction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostShowContentDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
        val post = viewModel.getPost(args.idPost)

        if (post != null) {
            val popupMenu by lazy {
                PopupMenu(context, binding.postContentDetail.menu).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                viewModel.onRemoveClicked(post)
                                findNavController().run {
                                    popBackStack()
                                    navigate(R.id.feedFragment)
                                }
                                true
                            }
                            R.id.edit -> {
                                viewModel.onEditClicked(post)

                                true
                            }
                            else -> false
                        }
                    }
                }
            }

            with(binding.postContentDetail) {
                like.setOnClickListener { onLikeClickedUser(post) }
                reposts.setOnClickListener { viewModel.onRepostClicked(post) }
                menu.setOnClickListener { popupMenu.show() }
                videoPreview.setOnClickListener { viewModel.onPlayVideoClicked(post) }
                videoPreviewButtonPlay.setOnClickListener { viewModel.onPlayVideoClicked(post) }
                with(binding.postContentDetail) {
                    authorName.text = post.author
                    date.text = post.published
                    post.content.also { postBody.text = it }
                    like.text = getTextViewCount(post.likes)
                    like.isChecked = post.likedByMe
                    usersViews.text = getTextViewCount(post.views)
                    reposts.text = getTextViewCount(post.reposts)
                 // avatar.setImageResource(R.drawable.ic_new_avatar_48)
                    avatar.setImageResource(if (post.avatar == "0") R.drawable.ic_new_avatar_48 else post.avatar.toInt())

                    if (post.videoAttachmentCover != null) {
                        videoPreview.setImageResource(post.videoAttachmentCover.toInt())
                        videoTitle.text = post.videoAttachmentHeader
                        videoPreview.visibility = View.VISIBLE
                        videoTitle.visibility = View.VISIBLE
                        videoPreviewButtonPlay.visibility = View.VISIBLE
                    } else {
                        videoPreview.visibility = View.GONE
                        videoTitle.visibility = View.GONE
                        videoPreviewButtonPlay.visibility = View.GONE
                    }
                }
            }

        } else findNavController().popBackStack()
    }.root

    private fun onLikeClickedUser(post: Post) {
        viewModel.onLikeClicked(post)



        findNavController().apply {
            onDetach()
            onAttach(context)
        }
    }
}







