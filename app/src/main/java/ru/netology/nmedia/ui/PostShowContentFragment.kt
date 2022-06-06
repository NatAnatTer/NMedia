package ru.netology.nmedia.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.NonDisposableHandle.parent
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.getTextViewCount
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.FilePostRepository
import ru.netology.nmedia.databinding.PostListItemBinding
import ru.netology.nmedia.databinding.PostShowContentDetailFragmentBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.postViewModel.PostShowDetailViewModel

//private const val ID_POST_ARG = "PostId"

class PostShowContentFragment : Fragment() {

    private val viewModel by viewModels<PostShowDetailViewModel>()
    private val args by navArgs<PostShowContentFragmentArgs>()


    val post = viewModel.currentPost.value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //  val repository: PostRepository = FilePostRepository()
      //  val post1 = FilePostRepository.getPost(args)

    }

  //          @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostShowContentDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
     // val view = PostListItemBinding.inflate(layoutInflater,container,false)
      if(post != null){
          with(binding.postContentDetail){
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
          binding.postContentDetail.authorName.text = post.author
      } else  findNavController().popBackStack()



     //   binding.textPost.text =
        //    "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"+"\n"+
         //           "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"

    }.root

//    companion object {
//        fun newInstance(postId: Long): PostContentFragment{
//            val args = Bundle().apply {
//                putSerializable(ID_POST_ARG, postId)
//            }
//            return PostContentFragment().apply {
//                arguments = args
//            }
//        }
//    }


}




  //  @SuppressLint("QueryPermissionsNeeded")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        viewModel.sharePostContent.observe(this) { postContent ->
//            val intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, postContent)
//                type = "text/plain"
//            }
//            val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
//            startActivity(shareIntent)
//        }
//
//        viewModel.videoLinkPlay.observe(this) { videoLink ->
//            val intent = Intent(Intent.ACTION_VIEW).apply {
//                val uri = Uri.parse(videoLink)
//                data = uri
//            }
//            val openVideoIntent =
//                Intent.createChooser(intent, getString(R.string.chooser_play_video))
//            startActivity(openVideoIntent)
//        }
//
//        setFragmentResultListener(requestKey = PostContentFragment.REQUEST_KEY) { requestKey, bundle ->
//            if (requestKey != PostContentFragment.REQUEST_KEY) return@setFragmentResultListener
//            val newPostContent =
//                bundle.getString(PostContentFragment.RESULT_KEY) ?: return@setFragmentResultListener
//            viewModel.onSaveButtonClicked(newPostContent)
//        }
//
//
//        viewModel.navigateToPostContentScreenEvent.observe(this) { initialContent ->
//            val direction = FeedFragmentDirections.toPostContentFragment(initialContent)
//            findNavController().navigate(direction)
//
//        }
//
//    }



//    private val inflater: LayoutInflater = LayoutInflater.from(context)
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ) = PostShowContentDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
//            PostListItemBi(PostListItemBinding.inflate(layoutInflater))
//
//
//    }.root
//
//    class PostListItemBi(private val binding: PostListItemBinding) {
//        fun bind(post: Post) {
//            //val binding = PostListItemBinding.inflate(layoutInflater)
//            with(binding) {
//                authorName.text = post.author
//                date.text = post.published
//                post.content.also { postBody.text = it }
//                like.text = getTextViewCount(post.likes)
//                like.isChecked = post.likedByMe
//                usersViews.text = getTextViewCount(post.views)
//                reposts.text = getTextViewCount(post.reposts)
//                avatar.setImageResource(post.avatar)
//                if (post.videoAttachmentCover != null) {
//                    videoPreview.setImageResource(post.videoAttachmentCover)
//                    videoTitle.text = post.videoAttachmentHeader
//                    videoPreview.visibility = View.VISIBLE
//                    videoTitle.visibility = View.VISIBLE
//                    videoPreviewButtonPlay.visibility = View.VISIBLE
//                } else {
//                    videoPreview.visibility = View.GONE
//                    videoTitle.visibility = View.GONE
//                    videoPreviewButtonPlay.visibility = View.GONE
//                }
//            }
//        }
//    }
//}



//<include
//android:id="@+id/post_content_detail"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//app:layout_constraintTop_toTopOf="parent"
//layout="@layout/post_list_item" />





//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ) = PostShowContentDetailFragmentBinding.inflate(inflater, container, false).also { binding ->
//        val binding1: PostListItemBinding
//        val listener: PostShowDetailInteractionListener
//        val popupMenu by lazy {
//            PopupMenu(inflater.context, binding1.menu).apply {
//                inflate(R.menu.options_post)
//                setOnMenuItemClickListener { menuItem ->
//                    when (menuItem.itemId) {
//                        R.id.remove -> {
//                            listener.onRemoveClicked()
//                            true
//                        }
//                        R.id.edit -> {
//                            listener.onEditClicked()
//                            true
//                        }
//                        else -> false
//                    }
//                }
//            }
//        }
//
//
//        binding1.like.setOnClickListener { listener.onLikeClicked() }
//        binding1.reposts.setOnClickListener { listener.onRepostClicked() }
//        binding1.menu.setOnClickListener { popupMenu.show() }
//        binding1.videoPreview.setOnClickListener { listener.onPlayVideoClicked() }
//        binding1.videoPreviewButtonPlay.setOnClickListener { listener.onPlayVideoClicked() }
//
//
//
//        fun bind(post: Post) {
//            //  this.post = post
//            with(binding1) {
//                authorName.text = post.author
//                date.text = post.published
//                post.content.also { postBody.text = it }
//                like.text = getTextViewCount(post.likes)
//                like.isChecked = post.likedByMe
//                usersViews.text = getTextViewCount(post.views)
//                reposts.text = getTextViewCount(post.reposts)
//                avatar.setImageResource(post.avatar)
//                if (post.videoAttachmentCover != null) {
//                    videoPreview.setImageResource(post.videoAttachmentCover)
//                    videoTitle.text = post.videoAttachmentHeader
//                    videoPreview.visibility = View.VISIBLE
//                    videoTitle.visibility = View.VISIBLE
//                    videoPreviewButtonPlay.visibility = View.VISIBLE
//                } else {
//                    videoPreview.visibility = View.GONE
//                    videoTitle.visibility = View.GONE
//                    videoPreviewButtonPlay.visibility = View.GONE
//                }
//
//            }
//        }
//
//    }.root


//    @SuppressLint("ViewConstructor")
//    class ViewHolderPost(
//        private val binding: PostListItemBinding,
//        private val listener: PostShowDetailInteractionListener
//    )  {
//        val popupMenu by lazy {
//            PopupMenu(inflter.getContext, binding.menu).apply {
//                inflate(R.menu.options_post)
//                setOnMenuItemClickListener { menuItem ->
//                    when (menuItem.itemId) {
//                        R.id.remove -> {
//                            listener.onRemoveClicked()
//                            true
//                        }
//                        R.id.edit -> {
//                            listener.onEditClicked()
//                            true
//                        }
//                        else -> false
//                    }
//                }
//            }
//        }
//
//        init {
//            binding.like.setOnClickListener { listener.onLikeClicked() }
//            binding.reposts.setOnClickListener { listener.onRepostClicked() }
//            binding.menu.setOnClickListener { popupMenu.show() }
//            binding.videoPreview.setOnClickListener { listener.onPlayVideoClicked() }
//            binding.videoPreviewButtonPlay.setOnClickListener { listener.onPlayVideoClicked() }
//
//        }
//
//
//        fun bind(post: Post) {
//            //  this.post = post
//            with(binding) {
//                authorName.text = post.author
//                date.text = post.published
//                post.content.also { postBody.text = it }
//                like.text = getTextViewCount(post.likes)
//                like.isChecked = post.likedByMe
//                usersViews.text = getTextViewCount(post.views)
//                reposts.text = getTextViewCount(post.reposts)
//                avatar.setImageResource(post.avatar)
//                if (post.videoAttachmentCover != null) {
//                    videoPreview.setImageResource(post.videoAttachmentCover)
//                    videoTitle.text = post.videoAttachmentHeader
//                    videoPreview.visibility = View.VISIBLE
//                    videoTitle.visibility = View.VISIBLE
//                    videoPreviewButtonPlay.visibility = View.VISIBLE
//                } else {
//                    videoPreview.visibility = View.GONE
//                    videoTitle.visibility = View.GONE
//                    videoPreviewButtonPlay.visibility = View.GONE
//                }
//
//            }
//        }
//    }


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


