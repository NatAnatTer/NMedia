package ru.netology.nmedia.ui

import android.annotation.SuppressLint
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
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.adapter.getTextViewCount
import ru.netology.nmedia.databinding.FeedFragmentBinding
import ru.netology.nmedia.databinding.PostContentFragmentBinding
import ru.netology.nmedia.databinding.PostShowContentFragmentBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.postViewModel.PostShowDetailViewModel



class PostShowContentFragment : Fragment() {


    private val viewModel by viewModels<PostShowDetailViewModel>()
    private val args by navArgs<postShowContentFragment>()

    @SuppressLint("QueryPermissionsNeeded")
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
            val direction = FeedFragmentDirections.toPostContentFragment(initialContent)
            findNavController().navigate(direction)

        }

    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ) = FeedFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
//        val adapter = PostsAdapter(viewModel)
//        binding.postRecyclerView.adapter = adapter
//        viewModel.data.observe(viewLifecycleOwner) { posts ->
//            adapter.submitList(posts)
//        }
//    }.root
//override fun onCreateView(
//    inflater: LayoutInflater,
//    container: ViewGroup?,
//    savedInstanceState: Bundle?
//
//) = PostContentFragmentBinding.inflate(layoutInflater).also { binding ->
//    binding.edit.setText(args.initialContent)
//    binding.edit.requestFocus()
//    if (args.initialContent != null) {
//        binding.headerText.text = getString(R.string.headerTextEdit)
//    } else {
//        binding.headerText.text = getString(R.string.headerTextCreate)
//    }
//    binding.returnFromCreatePost.setOnClickListener {
//        findNavController().popBackStack()
//    }
//    binding.ok.setOnClickListener {
//        onOkButtonClicked(binding)
//    }
//
//}.root

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ) = PostShowContentFragmentBinding.inflate(layoutInflater, container, false).also { binding ->


       val popupMenu by lazy {
            PopupMenu(context, binding.menu).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemoveClicked()
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked()
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
            binding.like.setOnClickListener { listener.onLikeClicked() }
            binding.reposts.setOnClickListener { listener.onRepostClicked() }
            binding.menu.setOnClickListener { popupMenu.show() }
            binding.videoPreview.setOnClickListener { listener.onPlayVideoClicked() }
            binding.videoPreviewButtonPlay.setOnClickListener { listener.onPlayVideoClicked() }

        }


        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                date.text = post.published
                post.content.also { postBody.text = it }
                like.text = getTextViewCount(post.likes)
                like.isChecked = post.likedByMe
                usersViews.text = getTextViewCount(post.views)
                reposts.text = getTextViewCount(post.reposts)
                avatar.setImageResource(post.avatar)
                if (post.videoAttachmentCover != null) {
                    videoPreview.setImageResource(post.videoAttachmentCover)
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

    }.root





//    private val args by navArgs<PostContentFragmentArgs>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//
//    ) = PostContentFragmentBinding.inflate(layoutInflater).also { binding ->
//        binding.edit.setText(args.initialContent)
//        binding.edit.requestFocus()
//        if (args.initialContent != null) {
//            binding.headerText.text = getString(R.string.headerTextEdit)
//        } else {
//            binding.headerText.text = getString(R.string.headerTextCreate)
//        }
//        binding.returnFromCreatePost.setOnClickListener {
//            findNavController().popBackStack()
//        }
//        binding.ok.setOnClickListener {
//            onOkButtonClicked(binding)
//        }
//
//    }.root
//
//    private fun onOkButtonClicked(binding: PostContentFragmentBinding) {
//        val text = binding.edit.text
//        if (!text.isNullOrBlank()) {
//            val resultBundle = Bundle(1)
//            val content = text.toString()
//            resultBundle.putString(RESULT_KEY, content)
//            setFragmentResult(REQUEST_KEY, resultBundle)
//        }
//        findNavController().popBackStack()
//    }
//
//    companion object {
//        const val RESULT_KEY = "postNewContent"
//        const val POST_BODY_TEXT = "Post body"
//        const val REQUEST_KEY = "requestKey"
//
//    }

}
