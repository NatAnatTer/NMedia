package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.netology.nmedia.R
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
        binding.fab.setOnClickListener {
            viewModel.onAddButtonClicked()
        }

//        binding.saveButton.setOnClickListener {
//            with(binding.contentEditText) {
//                val content = text.toString()
//                viewModel.onSaveButtonClicked(content)
//                text = null
//                clearFocus()
//                hideKeyboard()
//            }
//            binding.group.visibility = View.GONE
//        }
//        binding.group.visibility = View.GONE
//
//        viewModel.currentPost.observe(this) { currentPost ->
//            if (currentPost != null) {
//                binding.group.visibility = View.VISIBLE
//                binding.oldContentEditedText.text = currentPost.content
//                binding.contentEditText.setText(currentPost.content)
//                binding.undoEditButton.setOnClickListener {
//                    binding.contentEditText.text = null
//                    viewModel.onUndoEditClicked(currentPost)
//                    binding.group.visibility = View.GONE
//                    binding.contentEditText.clearFocus()
//                    binding.contentEditText.hideKeyboard()
//
//                }
//            }
//            binding.contentEditText.setText(currentPost?.content)
//        }

        viewModel.sharePostContent.observe(this){postContent ->
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, postContent)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
            startActivity(shareIntent)
        }

    }
}





//<androidx.constraintlayout.widget.Barrier
//android:id="@+id/toolsTopBarrier"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//app:barrierDirection="top"
//app:constraint_referenced_ids="contentEditText,saveButton" />
//
//<EditText
//android:id="@+id/contentEditText"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:background="@android:color/transparent"
//android:hint="@string/post_text_hint"
//android:importantForAutofill="no"
//android:inputType="textMultiLine"
//android:padding="@dimen/common_spacing"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toStartOf="@id/saveButton"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="@id/toolsTopBarrier"
//tools:hint="@string/post_text_hint" />
//
//<ImageButton
//android:id="@+id/saveButton"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="@dimen/common_spacing"
//android:background="@android:color/transparent"
//android:contentDescription="@string/description_post_like"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toEndOf="@id/contentEditText"
//app:srcCompat="@drawable/ic_baseline_send_24" />
//
//
//<androidx.constraintlayout.widget.Group
//android:id="@+id/group"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:visibility="visible"
//app:constraint_referenced_ids="undoEditingTopBarrier, edit_icon,oldContentEditedText,undoEditButton, header_edited_text" />
//
//<androidx.constraintlayout.widget.Barrier
//android:id="@+id/undoEditingTopBarrier"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//app:barrierDirection="top"
//app:constraint_referenced_ids="contentEditText,saveButton, edit_icon" />
//
//<androidx.appcompat.widget.AppCompatImageView
//android:id="@+id/edit_icon"
//android:layout_width="@dimen/icons_size"
//android:layout_height="@dimen/icons_size"
//android:src="@drawable/ic_baseline_edit_24"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintEnd_toStartOf="@id/oldContentEditedText"
//app:layout_constraintTop_toBottomOf="@id/undoEditingTopBarrier"
//app:layout_constraintBottom_toTopOf="@id/toolsTopBarrier"
///>
//
//<androidx.appcompat.widget.AppCompatTextView
//android:id="@+id/header_edited_text"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:background="@android:color/transparent"
//android:ellipsize="end"
//android:singleLine="true"
//android:layout_marginStart="16dp"
//android:layout_marginEnd="8dp"
//android:text="@string/edit_header"
//app:layout_constraintBottom_toTopOf="@id/oldContentEditedText"
//app:layout_constraintEnd_toStartOf="@id/undoEditButton"
//app:layout_constraintStart_toEndOf="@id/edit_icon"
//app:layout_constraintTop_toTopOf="@id/undoEditingTopBarrier"
///>
//<androidx.appcompat.widget.AppCompatTextView
//android:id="@+id/oldContentEditedText"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:background="@android:color/transparent"
//android:ellipsize="end"
//android:singleLine="true"
//android:layout_marginStart="16dp"
//android:layout_marginEnd="8dp"
//app:layout_constraintBottom_toTopOf="@id/toolsTopBarrier"
//app:layout_constraintEnd_toStartOf="@id/undoEditButton"
//app:layout_constraintStart_toEndOf="@id/edit_icon"
//app:layout_constraintTop_toBottomOf="@id/header_edited_text"
//tools:text="old post text"
///>
//
//<ImageButton
//android:id="@+id/undoEditButton"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="@dimen/common_spacing"
//android:background="@android:color/transparent"
//android:contentDescription="@string/description_undo_editing"
//app:layout_constraintBottom_toTopOf="@id/toolsTopBarrier"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toEndOf="@id/oldContentEditedText"
//app:layout_constraintTop_toBottomOf="@id/undoEditingTopBarrier"
//app:srcCompat="@drawable/ic_baseline_close_24" />









