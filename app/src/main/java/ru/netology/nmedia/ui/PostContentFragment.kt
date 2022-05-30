package ru.netology.nmedia.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostContentFragmentBinding

class PostContentFragment : Fragment() {

  //  supportActionBar?.hide() - TODO refactor


    private val args by navArgs<PostContentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ) = PostContentFragmentBinding.inflate(layoutInflater).also { binding ->

        binding.edit.setText(args.initialContent)
        binding.edit.requestFocus()
        val intent = Intent()
        val intentGetText: Bundle? = intent.extras
        val textBodyPost = intentGetText?.get(POST_BODY_TEXT).toString()
        if (textBodyPost != "null") {
            binding.headerText.text = getString(R.string.headerTextEdit)
            binding.edit.setText(textBodyPost)
        } else {
            binding.headerText.text = getString(R.string.headerTextCreate)
        }
        binding.returnFromCreatePost.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.ok.setOnClickListener {
            onOkButtonClicked(binding)
        }

    }.root

    private fun onOkButtonClicked(binding: PostContentFragmentBinding) {
        val text = binding.edit.text
        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(1)
            val content = text.toString()
            resultBundle.putString(RESULT_KEY, content)
            setFragmentResult(REQUEST_KEY, resultBundle)
        }
            findNavController().popBackStack()
    }

    companion object {
        const val RESULT_KEY = "postNewContent"
        const val POST_BODY_TEXT = "Post body"
        const val REQUEST_KEY = "requestKey"

    }
}
