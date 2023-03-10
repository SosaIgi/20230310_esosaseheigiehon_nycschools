package com.example.a20230310_esosaseheigiehon_nycschools

import com.example.a20230310_esosaseheigiehon_nycschools.network.NYCOpenDataNetworkModule
import com.example.a20230310_esosaseheigiehon_nycschools.repo.SchoolsRepositoryModule
import com.example.a20230310_esosaseheigiehon_nycschools.viewmodel.SchoolViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test

class ViewModelTest {

    private lateinit var viewModel: SchoolViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModel() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            viewModel = SchoolViewModel(
                SchoolsRepositoryModule.providesSchoolRepository(
                    NYCOpenDataNetworkModule.providesNYCOpenDataApi(
                        NYCOpenDataNetworkModule.providesRetrofit()
                    )
                )
            )

            viewModel.fetchSchools()
            print(viewModel.lState.value)
            assertEquals( com.example.a20230310_esosaseheigiehon_nycschools.model.Result.SUCCESS, viewModel.lState.value?.message)

        } finally {
            Dispatchers.resetMain()
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun settingMainDispatcher() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            viewModel = SchoolViewModel(
                SchoolsRepositoryModule.providesSchoolRepository(
                    NYCOpenDataNetworkModule.providesNYCOpenDataApi(
                        NYCOpenDataNetworkModule.providesRetrofit()
                    )
                )
            )
            viewModel.loadMessage() // Uses testDispatcher, runs its coroutine eagerly
            Assert.assertEquals("Greetings!", viewModel.message.value)
        } finally {
            Dispatchers.resetMain()
        }
    }
}