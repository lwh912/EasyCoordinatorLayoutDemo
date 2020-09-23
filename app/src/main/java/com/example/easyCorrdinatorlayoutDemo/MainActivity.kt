package com.easycorrdinatorlayoutdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyCorrdinatorlayoutDemo.DemoActivity
import com.example.easyCorrdinatorlayoutDemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_btn.setOnClickListener{
            val intent = Intent(this, DemoActivity::class.java)
            intent.putExtra("toolbarHeight",et_tool_height.text.toString())
            intent.putExtra("tagbarHeight",et_tag_height.text.toString())
            startActivity(intent)
        }
    }
}