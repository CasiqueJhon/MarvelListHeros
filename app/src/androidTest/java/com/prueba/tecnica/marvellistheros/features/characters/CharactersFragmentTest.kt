package com.prueba.tecnica.marvellistheros.features.characters

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prueba.tecnica.marvellistheros.R
import com.prueba.tecnica.marvellistheros.base.FakeCharactersViewModel
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetCharactersUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetListModeUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveListModeUseCase
import com.prueba.tecnica.marvellistheros.features.characters.presentation.CharactersFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {

    private lateinit var scenario: FragmentScenario<CharactersFragment>

    private val mockGetCharactersUseCase = mockk<GetCharactersUseCase>()
    private val mockSaveFavoriteUseCase = mockk<SaveFavoriteUseCase>()
    private val mockListModeUseCase = mockk<SaveListModeUseCase>()
    private val mockGetListModeUseCase = mockk<GetListModeUseCase>()

    @Test
    fun launchCharactersFragmentAndVerifyUI() {
        launchFragmentInContainer<CharactersFragment>()

        Espresso.onView(withId(R.id.characters_refresh))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun responseIsSuccessThenJustListIsVisible() {
        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorThenJustPlaceholderIsVisible() {
        scenarioNetworkError()
    }

    @Test
    fun responseIsGenericErrorThenJustPlaceholderIsVisible() {
        scenarioGenericError()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithSuccessThenListAreRefreshed() {
        scenarioSuccess()

        Espresso.onView(withId(R.id.characters_refresh)).perform(ViewActions.swipeDown())

        scenarioSuccess()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithNetworkErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        Espresso.onView(withId(R.id.characters_refresh)).perform(ViewActions.swipeDown())

        scenarioNetworkError()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithGenericErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        Espresso.onView(withId(R.id.characters_refresh)).perform(ViewActions.swipeDown())

        scenarioGenericError()
    }

    @Test
    fun responseIsNetworkErrorAndUserSwipeDownWithSuccessThenListAreRefreshed() {
        scenarioNetworkError()

        Espresso.onView(withId(R.id.characters_refresh)).perform(ViewActions.swipeDown())

        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorAndUserSwipeDownWithNetworkErrorAndPlaceholderIsShown() {
        scenarioNetworkError()

        Espresso.onView(withId(R.id.characters_refresh)).perform(ViewActions.swipeDown())

        scenarioNetworkError()
    }

    // region Scenarios
    private fun scenarioSuccess() {
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.SUCCESS
                )
            }
        }

        Espresso.onView(withId(R.id.characters_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.charactersPlaceholder))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.characters_list_loader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.charactersProgressBar))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun scenarioNetworkError() {
        scenario = launchFragmentInContainer {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.NETWORK_ERROR
                )
            }
        }

        Espresso.onView(withId(R.id.characters_list))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.charactersPlaceholder))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.characters_list_loader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.charactersProgressBar))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun scenarioGenericError() {
        scenario = launchFragmentInContainer {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.GENERIC_ERROR
                )
            }
        }

        Espresso.onView(withId(R.id.characters_list))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.charactersPlaceholder))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.characters_list_loader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.charactersProgressBar))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
    // endregion
}