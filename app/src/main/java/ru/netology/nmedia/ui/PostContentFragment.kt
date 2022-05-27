package ru.netology.nmedia.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FeedFragmentBinding
import ru.netology.nmedia.databinding.PostContentFragmentBinding

//import ru.netology.nmedia.databinding.PostContentActivityBinding


class PostContentFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //  supportActionBar?.hide() - TODO refactor
//
//
//    }

    private val initialContent
        get() = requireArguments().getString(INITIAL_CONTENT_ARGUMENT_KEY)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostContentFragmentBinding.inflate(layoutInflater).also { binding ->

        binding.edit.setText(initialContent)
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
        //parentFragmentManager.popBackStack()
        findNavController().popBackStack()
    }

//    object ResultContract : ActivityResultContract<String?, String?>() {
//        override fun createIntent(context: Context, input: String?): Intent {
//            return Intent(context, PostContentFragment::class.java)
//                .putExtra(POST_BODY_TEXT, input)
//        }
//
//        override fun parseResult(resultCode: Int, intent: Intent?) =
//            if (resultCode == Activity.RESULT_OK) {
//                intent?.getStringExtra(RESULT_KEY)
//            } else null
//    }

    companion object {
        private const val INITIAL_CONTENT_ARGUMENT_KEY = "initialContent"
        const val RESULT_KEY = "postNewContent"
        const val POST_BODY_TEXT = "Post body"
        const val REQUEST_KEY = "requestKey"
        fun create(initialContent: String?) = PostContentFragment().apply {
            arguments = createBundle(initialContent)
        }

        fun createBundle(initialContent: String?) = Bundle(1).apply {
            putString(INITIAL_CONTENT_ARGUMENT_KEY, initialContent)
        }
    }
}
