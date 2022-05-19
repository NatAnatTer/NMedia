package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostContentActivityBinding


class PostContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding = PostContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.requestFocus()


        val intentGetText: Bundle? = intent.extras
        val textBodyPost = intentGetText?.get(POST_BODY_TEXT).toString()
        if (textBodyPost != "null") {
            binding.headerText.text = getString(R.string.headerTextEdit)
            binding.edit.setText(textBodyPost)
        } else {
            binding.headerText.text = getString(R.string.headerTextCreate)
        }

        val text = binding.edit.text

        binding.returnFromCreatePost.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.ok.setOnClickListener {
            val intent = Intent()
            if (text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = text.toString()
                intent.putExtra(RESULT_KEY, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

    object ResultContract : ActivityResultContract<String?, String?>() {
        override fun createIntent(context: Context, input: String?): Intent {
            return Intent(context, PostContentActivity::class.java)
                .putExtra(POST_BODY_TEXT, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (resultCode == Activity.RESULT_OK) {
                intent?.getStringExtra(RESULT_KEY)
            } else null
    }

    companion object {
        private const val RESULT_KEY = "postNewContent"
        private const val POST_BODY_TEXT = "Post body"
    }
}
