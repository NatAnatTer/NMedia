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
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.NonDisposableHandle.parent
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.getTextViewCount
import ru.netology.nmedia.databinding.PostListItemBinding
import ru.netology.nmedia.databinding.PostShowContentDetailFragmentBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.postViewModel.PostViewModel


class PostShowContentFragment : Fragment() {

    private val args by navArgs<PostShowContentFragmentArgs>()
    private val viewModel by viewModels<PostViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostShowContentDetailFragmentBinding.inflate(layoutInflater).also { binding ->
        val post = viewModel.getPost(args.idPost)
        if (post != null) {
            with(binding.postContentDetail) {
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
        } else findNavController().popBackStack()
    }.root


}


//<include
//android:id="@+id/post_content_detail"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//app:layout_constraintTop_toTopOf="parent"
//layout="@layout/post_list_item" />

//<TextView
//android:id="@+id/textPost"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//tools:text="@tools:sample/lorem/random" />



