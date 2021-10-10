package com.dicoding.todoapp.ui.list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.todoapp.R
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
@RunWith(AndroidJUnit4::class)
class TaskActivityTest {
    @Before
    fun setUp(){
        ActivityScenario.launch(TaskActivity::class.java)
    }

    @Test
    fun test_when_tap_add_task_and_then_AddTaskActivity_displayed() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.fab),
                ViewMatchers.isDisplayed()
            )
        )
            .perform(ViewActions.click())

        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.add_ed_title),
                ViewMatchers.isDisplayed()
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}