package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.netology.nmedia.postViewModel.PostViewModel
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.util.hideKeyboard

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = PostsAdapter(viewModel)
        binding.postRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                val content = text.toString()
                viewModel.onSaveButtonClicked(content)
                text = null
                clearFocus()
                hideKeyboard()
            }
            binding.group.visibility = View.GONE
        }
        binding.group.visibility = View.GONE

        viewModel.currentPost.observe(this) { currentPost ->
            if (currentPost != null) {
                binding.group.visibility = View.VISIBLE
                binding.oldContentEditedText.text = currentPost.content
                binding.contentEditText.setText(currentPost.content)
                binding.undoEditButton.setOnClickListener {
                    binding.contentEditText.text = null
                    viewModel.onUndoEditClicked(currentPost)
                    binding.group.visibility = View.GONE
                    binding.contentEditText.clearFocus()
                    binding.contentEditText.hideKeyboard()

                }
            }
            binding.contentEditText.setText(currentPost?.content)
        }

    }
}









