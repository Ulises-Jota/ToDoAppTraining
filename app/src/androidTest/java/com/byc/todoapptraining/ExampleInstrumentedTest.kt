package com.byc.todoapptraining

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.byc.todoapptraining.addtasks.ui.TasksScreen
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    // To testing Composables
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.byc.todoapptraining", appContext.packageName)
    }

    @Test
    fun someScreen() {
        composeTestRule.apply {
            setContent {
                TasksScreen(tasksViewModel = mockk())
            }

            // FINDERS (find a component)
            onNodeWithText("something")
            onNodeWithTag("something")
            onNodeWithContentDescription("something")

            // ACTIONS (perform an action)
            onNodeWithText("something", ignoreCase = true).performClick()
            onAllNodesWithText("something").onFirst().performTextInput("other thing")
            onNodeWithTag("something").performTouchInput { doubleClick() }
            // The Ime Action is the keyboard action button (for example, an Enter)
            onNodeWithTag("something").performImeAction()

            // ASSERTIONS (check final status)
            onNodeWithText("something").assertExists()
            onNodeWithText("something").assertDoesNotExist()
            onNodeWithText("something").assertContentDescriptionContains("some")
            onNodeWithText("something").assertIsDisplayed()
            onNodeWithText("something").performClick().assertIsFocused()
        }
    }
}