package ru.netology.nmedia.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.postViewModel.PostViewModel
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.FeedFragmentBinding

//import ru.netology.nmedia.databinding.ActivityMainBinding

class FeedFragment : Fragment() {

    private val viewModel by viewModels<PostViewModel>()

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  val binding = ActivityMainBinding.inflate(layoutInflater)
        //  setContentView(binding.root)

//        val adapter = PostsAdapter(viewModel)
//        binding.postRecyclerView.adapter = adapter
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }
//
//        binding.fab.setOnClickListener {
//            viewModel.onAddButtonClicked()
//        }
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


//        val postContentActivityLauncher =
//            registerForActivityResult(PostContentActivity.ResultContract) { postContent ->
//                postContent ?: return@registerForActivityResult
//                viewModel.onSaveButtonClicked(postContent)
//
//            }

        viewModel.navigateToPostContentScreenEvent.observe(this) { initialContent ->
            //postContentActivityLauncher.launch(null)
            val direction = FeedFragmentDirections.toPostContentFragment(initialContent)
            findNavController().navigate(direction)
//            parentFragmentManager.commit {
//                val fragment = PostContentFragment.create(initialContent)
//                replace(R.id.fragment_container, fragment)
//                addToBackStack(null)
//            }
        }
//        viewModel.navigateToPostContentEditEvent.observe(this) {
//            if (it != null) {
//                parentFragmentManager.commit {
//                    val fragment = PostContentFragment()
//                    replace(R.id.fragment_container, fragment)
//                    addToBackStack(null)
//                }
//                //TODO prepare view text old post
//                //TODO text New or Edit not changed
//               // postContentActivityLauncher.launch(it.content)
//            }
//        }


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FeedFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = PostsAdapter(viewModel)
        binding.postRecyclerView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            viewModel.onAddButtonClicked()
        }
    }.root

    companion object {
        const val TAG = "FeedFragment"
    }
}
