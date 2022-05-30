package ru.denis.convertertorub

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shopify.testify.ScreenshotRule
import com.shopify.testify.TestifyFeatures
import com.shopify.testify.annotation.ScreenshotInstrumentation
import com.shopify.testify.annotation.TestifyLayout
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.denis.convertertorub.ui.MainActivity
import ru.denis.convertertorub.ui.currenciesfragment.CurrenciesFragment
import java.util.*

/**
 * - Testify создана на основе инструментальных тестов
 * - Расширяет ActivityTestRule
 *
 * Настройка:
 *  * Project - dependencies {
 *       classpath "com.shopify.testify:plugin:1.2.0-alpha01"
 *    }
 *  * App - plugins {
 *              id("com.shopify.testify")
 *          }
 *  * App - dependencies {
 *              androidTestImplementation "androidx.test:rules:1.4.0"
 *          }
 *
 * Команды:
 *  * ./gradlew screenshotRecord - создание или перезапись эталонных снимков
 *  * ./gradlew screenshotTest - тестировние, сравнение с текущим эталоном
 *  * ./gradlew screenshotPull - выгрузка скриншотов из папки устройства в папку для эталонов - assets
 *  * ./gradlew screenshotClear - удаление скриншотов с устройства
 */

@RunWith(AndroidJUnit4::class)
class CurrenciesFragmentScreenshotTest {

    @get:Rule
    var rule = ScreenshotRule(MainActivity::class.java)

    /**
     * ПРОСТОЙ ТЕСТ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `default test`() {
        Thread.sleep(3000)
        rule
            //.setTargetLayoutId(R.layout.currencies_fragment)
            .assertSame()
    }

    /**
     * СНИМОК КОНКРЕТНОЙ ВЬЮХИ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `concrete view shot`() {
        rule
            .setScreenshotViewProvider {
                it.findViewById(R.id.go_to_converter)
            }
            .assertSame()
    }

    /**
     * ИЗМЕНЕНИЕ ВОСПРИИМЧИВОСТИ К НЕСООТВЕТСВИЯМ
     */
    @ScreenshotInstrumentation
    @TestifyLayout(R.layout.currencies_fragment)
    @Test
    fun `test with exactness`() {
        Thread.sleep(3000)
        rule
            .setExactness(0.3f)
            .assertSame()
    }

    /**
     * ПЕРЕДАЧА BUNDLE
     */
    @ScreenshotInstrumentation
    @Test
    fun `test with bundle`() {
        rule
            .addIntentExtras {
                it.putString("TOOLBAR_TITLE", "addIntentExtras")
            }
            .assertSame()
    }

    /**
     * ПРИМЕНЕНИЕ ESPRESSO UI ТЕСТОВ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with espresso`() {
        rule
            .setEspressoActions {
                onView(withId(R.id.go_to_converter)).perform(click())
            }
            .assertSame()
    }

    /**
     * СМЕНА ОРИЕНТАЦИИ ЭКРАНА
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with orientation change`() {
        rule
            .setOrientation(requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE)
            .assertSame()
    }

    /**
     * ПРИМЕНЕНИЕ LAYOUT INSPECTOR
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with layout inspector`() {
        rule
            .setLayoutInspectionModeEnabled(true)
            .assertSame()
    }

    /**
     * ПЕРЕКЛЮЧЕНИЕ НА ПРОГРАМНЫЙ РЕНДЕРИНГ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with software renderer`() {
        rule
            .setUseSoftwareRenderer(true)
            .assertSame()
    }

    /**
     *ИСКЛЮЧЕНИЕ ОПРЕДЕЛЕННОЙ ОБЛАСТИ ИЗ ПРОВЕРКИ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with excluded area`() {
        rule
            .defineExclusionRects { rootView, exclusionRects ->
                val card = rootView.findViewById<View>(R.id.go_to_converter)
                exclusionRects.add(card.boundingBox)
            }
            .assertSame()
    }

    /**
     * ФОКУС НА КОНКРЕТНОЙ ВЬЮХЕ
     */
    @TestifyLayout(R.layout.currencies_fragment)
    @ScreenshotInstrumentation
    @Test
    fun `test with focus`() {
        rule
            .setFocusTarget(enabled = true, focusTargetId = R.id.code)
            .assertSame()
    }
}