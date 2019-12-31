package com.m1zyuk1.testpracticeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDirectoryButton.setOnClickListener {
            val directoryName = directoryNameEditText.text.toString()
            if (SampleUtilityObject.tryCreateDirectory(this, directoryName)) {
                Toast.makeText(this, "Create directory", Toast.LENGTH_SHORT).show()
                directoryNameEditText.text.clear()
            } else {
                Toast.makeText(this, "Cannot create directory", Toast.LENGTH_SHORT).show()
            }
        }

        getDirectoryNamesButton.setOnClickListener {
            val directoryNames = SampleUtilityObject.getDirectoryNames(this)
            if (directoryNames.isNotEmpty()) {
                directoryNameListTextView.text = directoryNames.joinToString(",")
            } else {
                directoryNameListTextView.text = "empty"
            }
        }
    }
}
