package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ShowroomData
import com.example.ui.screens.BookingScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.ShowroomScreen
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.WhatsAppGreen
import com.example.util.ContactHelper

enum class AppNavDestination(val label: String, val icon: ImageVector, val tag: String) {
    SHOWROOM("3D Showroom", Icons.Default.ViewInAr, "nav_showroom"),
    CATALOG("Catalog & QR", Icons.Default.MenuBook, "nav_catalog"),
    BOOKING("Book Event", Icons.Default.EventAvailable, "nav_booking"),
    CONTACT("Contact", Icons.Default.ContactPhone, "nav_contact")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScaffold() {
    val context = LocalContext.current
    var currentDestination by remember { mutableStateOf(AppNavDestination.SHOWROOM) }
    var preselectedItemForBooking by remember { mutableStateOf("") }

    // Handle back button to return to Showroom
    BackHandler(enabled = currentDestination != AppNavDestination.SHOWROOM) {
        currentDestination = AppNavDestination.SHOWROOM
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = ShowroomData.SHORT_NAME,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Mohanpur, Hailakandi • 788150",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldPrimary,
                                fontSize = 11.sp
                            )
                        )
                    }
                },
                actions = {
                    // Quick Call Action
                    IconButton(
                        onClick = { ContactHelper.dialPhone(context, ShowroomData.PHONE_PRIMARY) },
                        modifier = Modifier.testTag("top_bar_call_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call Laskar Decorators",
                            tint = GoldPrimary
                        )
                    }

                    // Quick WhatsApp Action
                    IconButton(
                        onClick = {
                            ContactHelper.openWhatsAppMessage(
                                context,
                                "Hello Laskar Tent Gift Mike House & Decorators, I want to book decoration & sound services for an event."
                            )
                        },
                        modifier = Modifier.testTag("top_bar_whatsapp_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "WhatsApp Chat",
                            tint = WhatsAppGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                AppNavDestination.values().forEach { destination ->
                    val isSelected = currentDestination == destination
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            currentDestination = destination
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.label
                            )
                        },
                        label = {
                            Text(
                                text = destination.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF140D02),
                            selectedTextColor = GoldPrimary,
                            indicatorColor = GoldPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag(destination.tag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentDestination) {
                AppNavDestination.SHOWROOM -> {
                    ShowroomScreen(
                        onNavigateToBooking = { itemName ->
                            preselectedItemForBooking = itemName
                            currentDestination = AppNavDestination.BOOKING
                        }
                    )
                }
                AppNavDestination.CATALOG -> {
                    CatalogScreen(
                        onNavigateToBooking = { itemName ->
                            preselectedItemForBooking = itemName
                            currentDestination = AppNavDestination.BOOKING
                        }
                    )
                }
                AppNavDestination.BOOKING -> {
                    BookingScreen(
                        initialPreselectedItem = preselectedItemForBooking
                    )
                }
                AppNavDestination.CONTACT -> {
                    ContactScreen()
                }
            }
        }
    }
}
