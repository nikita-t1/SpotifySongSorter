package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.MyTheme.THEME_BASIC
import ui.theme.MyTheme.THEME_MODE_AUTO
import ui.theme.MyTheme.THEME_MODE_OFF
import ui.theme.MyTheme.THEME_MODE_ON
import ui.theme.MyTheme.dimens

@Composable
fun MyThemeWrapper(content: @Composable () -> Unit) {
    val currentTheme by mutableStateOf(MyTheme.THEME_BASIC)
    val darkMode by mutableStateOf(THEME_MODE_AUTO)

    val isDarkMode = selectIsDarkMode(darkMode)
    val colors = selectColorPalette(currentTheme, isDarkMode)

    MyTheme(
        colors = colors,
        dimens = dimens,
        typography = MaterialTheme.typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun selectIsDarkMode(darkMode: String) = when (darkMode) {
    THEME_MODE_ON -> true
    THEME_MODE_OFF -> false
    THEME_MODE_AUTO -> isSystemInDarkTheme()
    else -> isSystemInDarkTheme()
}

@Composable
fun selectColorPalette(
    currentTheme: String,
    isDarkMode: Boolean
) = when (currentTheme) {
    THEME_BASIC -> {
        ColorPalette.basic(isDarkMode)
    }
    MyTheme.THEME_TURQUOISE -> {
        ColorPalette.turquoise(isDarkMode)
    }
    MyTheme.THEME_JASMINE -> {
        ColorPalette.jasmine(isDarkMode)
    }
    MyTheme.THEME_SUNSHADE -> {
        ColorPalette.sunShade(isDarkMode)
    }
    MyTheme.THEME_MARINEBLUE -> {
        ColorPalette.marineBlue(isDarkMode)
    }
    MyTheme.THEME_OUTERSPACE -> {
        ColorPalette.outerSpace(isDarkMode)
    }

    else -> {
        ColorPalette.basic(isDarkMode)
    }
}

@Composable
fun MyTheme(
    colors: MyColors,
    dimens: MyDimens,
    typography: Typography,
    shapes: Shapes,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        ColorPalette.basic(false)
    }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalReboundColors provides rememberedColors,
        LocalReboundDimens provides dimens,
        LocalReboundShapes provides shapes,
        LocalReboundTypography provides typography
    ) {
        MaterialTheme(
            colors = with(colors) {
                Colors(
                    primary = primary,
                    primaryVariant = primary,
                    secondary = secondary,
                    secondaryVariant = secondary,
                    background = background,
                    surface = background,
                    error = error,
                    onPrimary = onPrimary,
                    onSecondary = onPrimary,
                    onBackground = onBackground,
                    onSurface = onBackground,
                    onError = onError,
                    isLight = isLight
                )
            },
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

@Stable
class MyColors(
    isLight: Boolean,
    isDarkNavigationBarIcons: Boolean,
    isDarkStatusBarIcons: Boolean,
    primary: Color,
    secondary: Color,
    background: Color,
    error: Color = errorColor,
    onPrimary: Color,
    onBackground: Color,
    onError: Color = Color.White,
    card: Color,
    cardMainContent: Color,
    cardSecondaryContent: Color,
    topBar: Color = background,
    topBarTitle: Color,
    topBarIcons: Color,
    startButtonColor: Color,
    pauseButtonColor: Color,
    resetButtonColor: Color,
    navBar: Color,
    listCard: Color,
    onCard: Color = onBackground,
    selectedNavBarItemColor: Color = primary,
    unselectedNavBarItemColor: Color
) {
    var isLight by mutableStateOf(isLight, structuralEqualityPolicy())
        internal set
    var isDarkNavigationBarIcons by mutableStateOf(
        isDarkNavigationBarIcons,
        structuralEqualityPolicy()
    )
        internal set
    var isDarkStatusBarIcons by mutableStateOf(isDarkStatusBarIcons, structuralEqualityPolicy())
        internal set
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set
    var card by mutableStateOf(card, structuralEqualityPolicy())
        internal set
    var cardMainContent by mutableStateOf(cardMainContent, structuralEqualityPolicy())
        internal set
    var cardSecondaryContent by mutableStateOf(cardSecondaryContent, structuralEqualityPolicy())
        internal set
    var topBar by mutableStateOf(topBar, structuralEqualityPolicy())
        internal set
    var topBarTitle by mutableStateOf(topBarTitle, structuralEqualityPolicy())
        internal set
    var topBarIcons by mutableStateOf(topBarIcons, structuralEqualityPolicy())
        internal set
    var startButtonColor by mutableStateOf(startButtonColor, structuralEqualityPolicy())
        internal set
    var pauseButtonColor by mutableStateOf(pauseButtonColor, structuralEqualityPolicy())
        internal set
    var resetButtonColor by mutableStateOf(resetButtonColor, structuralEqualityPolicy())
        internal set
    var navBar by mutableStateOf(navBar, structuralEqualityPolicy())
        internal set
    var listCard by mutableStateOf(listCard, structuralEqualityPolicy())
        internal set
    var onCard by mutableStateOf(onCard, structuralEqualityPolicy())
        internal set
    var selectedNavBarItemColor by mutableStateOf(
        selectedNavBarItemColor,
        structuralEqualityPolicy()
    )
        internal set
    var unselectedNavBarItemColor by mutableStateOf(
        unselectedNavBarItemColor,
        structuralEqualityPolicy()
    )
        internal set
}

@Stable
class MyDimens(
    cardElevation: Dp,
    cardBorderWidth: Dp,
) {
    var cardElevation by mutableStateOf(cardElevation, structuralEqualityPolicy())
        internal set
    var cardBorderWidth by mutableStateOf(cardBorderWidth, structuralEqualityPolicy())
        internal set
}

object MyTheme {
    val colors: MyColors
        @Composable
        @ReadOnlyComposable
        get() = LocalReboundColors.current

    val dimens: MyDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalReboundDimens.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalReboundTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalReboundShapes.current

    const val THEME_BASIC = "basic"
    const val THEME_TURQUOISE = "turquoise"
    const val THEME_JASMINE = "jasmine"
    const val THEME_SUNSHADE = "sunshade"
    const val THEME_MARINEBLUE = "marineblue"
    const val THEME_OUTERSPACE = "outerSpace"

    val themes = listOf(
        THEME_BASIC,
        THEME_TURQUOISE,
        THEME_JASMINE,
        THEME_SUNSHADE,
        THEME_MARINEBLUE,
        THEME_OUTERSPACE
    )

    const val THEME_MODE_ON = "ON"
    const val THEME_MODE_OFF = "OFF"
    const val THEME_MODE_AUTO = "AUTO"

    val themeModes = listOf(THEME_MODE_ON, THEME_MODE_OFF, THEME_MODE_AUTO)
}

internal val LocalReboundColors = staticCompositionLocalOf { ColorPalette.basic(false) }
internal val LocalReboundDimens = staticCompositionLocalOf { defaultDimens() }
internal val LocalReboundTypography = staticCompositionLocalOf { Typography() }

fun defaultDimens(cardElevation: Dp = 0.dp, cardBorderWidth: Dp = 0.dp): MyDimens =
    MyDimens(
        cardElevation = cardElevation,
        cardBorderWidth = cardBorderWidth
    )

fun MyColors.updateColorsFrom(other: MyColors) {
    primary = other.primary
    secondary = other.secondary
    background = other.background
    error = other.error
    onPrimary = other.onPrimary
    onBackground = other.onBackground
    onError = other.onError
    isLight = other.isLight
    isDarkNavigationBarIcons = other.isDarkNavigationBarIcons
    isDarkStatusBarIcons = other.isDarkStatusBarIcons
    card = other.card
    cardMainContent = other.cardMainContent
    cardSecondaryContent = other.cardSecondaryContent
    topBar = other.topBar
    topBarTitle = other.topBarTitle
    topBarIcons = other.topBarIcons
    startButtonColor = other.startButtonColor
    pauseButtonColor = other.pauseButtonColor
    resetButtonColor = other.resetButtonColor
    listCard = other.listCard
    navBar = other.navBar
    onCard = other.onCard
    selectedNavBarItemColor = other.selectedNavBarItemColor
    unselectedNavBarItemColor = other.unselectedNavBarItemColor
}
