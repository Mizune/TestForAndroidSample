package com.m1zyuk1.testpracticeapp

import android.content.Context
import android.graphics.Bitmap
import com.nhaarman.mockito_kotlin.*
import javassist.tools.rmi.Sample
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.rules.TemporaryFolder
import org.junit.Rule
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
class SampleUnitTest {
    @Rule
    @JvmField
    val tempFolder: TemporaryFolder = TemporaryFolder()
    lateinit var rootDir: File

    @Before
    fun setuUp() {
        // test前に行う処理
        rootDir = tempFolder.root

    }

    @After
    fun tearDown() {
        // test後に行う処理
    }

    @Test
    fun testTextValidation() {
        // test処理
        val validName = "hoge"
        val invalidName = "hoge-fu.ga*"

        // 記号が含まれていないのでtrueになるのが仕様
        assertEquals(SampleUtilityObject.isValidName(validName), true)
        // 記号が含まれているのでfalseになるのが仕様
        assertEquals(SampleUtilityObject.isValidName(invalidName), false)
    }

    @Test
    fun testTryCreateDirectory() {
        val context = mock<Context> {
            on { cacheDir } doReturn rootDir
        }

        val alreadyExistName = "already"
        val validName = "valid"
        val invalidName = "not-valid_name/."

        // 先に１つディレクトリを作成
        rootDir.resolve(alreadyExistName).mkdir()

        // invalidな値を引数にするとfalseが返る
        assertFalse(SampleUtilityObject.tryCreateDirectory(context, invalidName))

        // 同じ名前で二度作成しようとするとfalseが帰ってくる
        assertFalse(SampleUtilityObject.tryCreateDirectory(context, alreadyExistName))

        // validならばtrueになる
        assertTrue(SampleUtilityObject.tryCreateDirectory(context, validName))

        // 作成されているディレクトリの名前はvalidNameである
        assertEquals(rootDir.listFiles().first().name, validName)
    }

    @Test
    fun testGetDirectoryNames() {
        val context = RuntimeEnvironment.application

        // validなディレクトリネーム
        val validName = "validName"
        val moreValidName = "moreValidName"
        val andMoreValidName = "andMoreValidName"

        // 仕様通り動作した場合の返り値
        val actualReturnValue = listOf(validName, moreValidName, andMoreValidName).sorted()

        // ディレクトリの作成
        SampleUtilityObject.tryCreateDirectory(context, validName)
        SampleUtilityObject.tryCreateDirectory(context, moreValidName)
        SampleUtilityObject.tryCreateDirectory(context, andMoreValidName)

        // 返り値のチェック
        assertEquals(SampleUtilityObject.getDirectoryNames(context), actualReturnValue)
    }
}
