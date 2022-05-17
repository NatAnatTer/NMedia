package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.PostContentActivityBinding

class PostContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding = PostContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.requestFocus()

        val text = binding.edit.text

        val textTitle = "Create post"
        binding.headerText.text = textTitle

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

    object ResultContract: ActivityResultContract<Unit, String?>(){
        override fun createIntent(context: Context, input: Unit) = Intent(context, PostContentActivity::class.java)

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if(resultCode == Activity.RESULT_OK){
                intent?.getStringExtra(RESULT_KEY)
            } else null

    }
    private companion object{
        private const val RESULT_KEY = "postNewContent"
    }
}



//<android.support.design.widget.CoordinatorLayout
//xmlns:android="http://schemas.android.com/apk/res/android"
//
//xmlns:app="http://schemas.android.com/apk/res-auto"
//
//android:id="@+id/main_content"
//android:layout_width="match_parent"
//android:layout_height="match_parent">
//<android.support.design.widget.AppBarLayout
//android:id="@+id/appbar"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
//<android.support.v7.widget.Toolbar
//android:id="@+id/toolbar"
//android:layout_width="match_parent"
//android:layout_height="?attr/actionBarSize"
//android:background="?attr/colorPrimary"
//app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
//app:layout_scrollFlags="scroll|enterAlways" />
//<android.support.design.widget.TabLayout
//android:id="@+id/tabs"
//android:layout_width="match_parent"
//android:layout_height="wrap_content" />
//</android.support.design.widget.AppBarLayout>
//<android.support.v4.view.ViewPager
//android:id="@+id/viewpager"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//app:layout_behavior="@string/appbar_scrolling_view_behavior" />
//<android.support.design.widget.FloatingActionButton
//android:id="@+id/fab"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="end|bottom"
//android:layout_margin="@dimen/fab_margin"
//android:src="@drawable/ic_done" />
//</android.support.design.widget.CoordinatorLayout>
//
//
//
//<?xml version="1.0" encoding="utf-8"?>
//<android.support.design.widget.CoordinatorLayout
//xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//android:layout_width="match_parent"
//android:layout_height="match_parent">
//<android.support.design.widget.FloatingActionButton
//android:id="@+id/fab"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_gravity="end|bottom"
//android:layout_margin="16dp"
//
//android:src="@drawable/ic_done" />
//</android.support.design.widget.CoordinatorLayout>
//
//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
//                    .setAction("cancel", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // Событие click здесь представляет событие ответа после нажатия, чтобы исключить Action
//                        }
//                    })
//                    .show();
//            }
//        });
//    }}