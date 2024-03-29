package com.byc.todoapptraining.addtasks.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class TasksScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tasksScreen() {
        composeTestRule.apply {
            setContent {
                TasksScreen(tasksViewModel = mockk())
            }

        }
    }

    @Test
    fun tasksList() {
    }

    @Test
    fun addTaskDialog() {
        composeTestRule.apply {
            setContent {
                AddTaskDialog(show = true, onDismiss = {}, onTaskAdded = {})
            }
            onNodeWithTag("Dialog column").assertIsDisplayed()
        }
    }
}