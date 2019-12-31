package com.m1zyuk1.testpracticeapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.VisibleForTesting
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object SampleUtilityObject {

    fun tryCreateDirectory(context: Context, directoryName: String): Boolean {
        if (!isValidName(directoryName)) {
            return false
        }

        if (hasDirectory(context, directoryName)) {
            return false
        }

        createDirectory(context, directoryName)

        return true
    }

    fun getDirectoryNames(context: Context): List<String> = context.cacheDir.listFiles().map { it.name }.sorted()

    private fun hasDirectory(context: Context, directoryName: String): Boolean =
            context.cacheDir.resolve(directoryName).exists()

    private fun createDirectory(context: Context, directoryName: String) {
        context.cacheDir.resolve(directoryName).mkdir()
    }

    @VisibleForTesting
    fun isValidName(name: String): Boolean =
            Regex("^[a-zA-Z0-9]+$").matches(name)
}
