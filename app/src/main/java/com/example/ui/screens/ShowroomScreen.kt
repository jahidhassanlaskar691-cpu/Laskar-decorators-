package com.example.ui.screens

import android.webkit.WebView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ShowroomData
import com.example.model.EventSpace
import com.example.model.HotspotItem
import com.example.ui.components.HotspotBottomSheet
import com.example.ui.components.Showroom3DView
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WhatsAppGreen
import com.example.util.ContactHelper

@Composable
fun ShowroomScreen(
    onNavigateToBooking: (String) -> Unit
) {
    val context = LocalContext.current
    var selectedSpaceId by remember { mutableStateOf("wedding") }
    var selectedHotspot by remember { mutableStateOf<HotspotItem?>(null) }
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }
    var isHotspotStripVisible by remember { mutableStateOf(true) }

    val currentSpace = remember(selectedSpaceId) {
        ShowroomData.eventSpaces.find { it.id == selectedSpaceId } ?: ShowroomData.eventSpaces.first()
    }

    val currentHotspots = remember(selectedSpaceId) {
        ShowroomData.hotspotList.filter { it.spaceId == selectedSpaceId }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0D0A14))) {
        // 1. Embedded 3D Hardware Accelerated WebGL Showroom View
        Showroom3DView(
            modifier = Modifier.fillMaxSize(),
            onHotspotSelected = { hotspot ->
                selectedHotspot = hotspot
            },
            onBookHotspot = { hotspot ->
                val msg = ContactHelper.createHotspotWhatsAppMessage(hotspot)
                ContactHelper.openWhatsAppMessage(context, msg)
            },
            onWebViewReady = { webView ->
                webViewInstance = webView
            }
        )

        // 2. Top Event Spaces Selector
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 10.dp, start = 12.dp, end = 12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF161020).copy(alpha = 0.92f),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(GoldPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "3D Virtual Showroom",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldLight
                                )
                            )
                        }

                        IconButton(
                            onClick = { isHotspotStripVisible = !isHotspotStripVisible },
                            modifier = Modifier.size(28.dp).testTag("toggle_hotspots_button")
                        ) {
                            Icon(
                                imageVector = if (isHotspotStripVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Hotspots",
                                tint = GoldPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Spaces Selector Chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(ShowroomData.eventSpaces) { space ->
                            val isSelected = space.id == selectedSpaceId
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = if (isSelected) GoldPrimary else Color(0xFF261C36),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable {
                                        selectedSpaceId = space.id
                                        webViewInstance?.evaluateJavascript("switchSpace('${space.id}')", null)
                                    }
                                    .testTag("space_chip_${space.id}")
                            ) {
                                Text(
                                    text = space.name,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color(0xFF120E18) else Color(0xFFE4DBF0)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Bottom Hotspot Quick Selector Overlay
        AnimatedVisibility(
            visible = isHotspotStripVisible && currentHotspots.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp, start = 12.dp, end = 12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF161020).copy(alpha = 0.94f),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Interactive Setups in this Space",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "Tap to Inspect",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFA69BB8)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(currentHotspots) { hotspot ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF251A36),
                                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                                modifier = Modifier
                                    .width(180.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        selectedHotspot = hotspot
                                        webViewInstance?.evaluateJavascript("selectHotspot(${com.example.util.jsonFromHotspot(hotspot)})", null)
                                    }
                                    .testTag("hotspot_item_${hotspot.id}")
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = hotspot.category.uppercase(),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = GoldPrimary
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = hotspot.title,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = hotspot.price,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color(0xFF4ADE80)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 4. Floating Action Button for Quick WhatsApp Inquiry
        FloatingActionButton(
            onClick = {
                val msg = "Hello Laskar Tent Gift Mike House & Decorators, I was exploring your 3D Showroom (${currentSpace.name}). I want to discuss decor, tent, and sound booking for my event."
                ContactHelper.openWhatsAppMessage(context, msg)
            },
            containerColor = WhatsAppGreen,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
                .testTag("floating_whatsapp_button")
        ) {
            Icon(
                imageVector = Icons.Default.Chat,
                contentDescription = "WhatsApp Inquiry",
                modifier = Modifier.size(24.dp)
            )
        }

        // 5. Hotspot Detail Modal Bottom Sheet
        selectedHotspot?.let { hotspot ->
            HotspotBottomSheet(
                item = hotspot,
                onDismiss = { selectedHotspot = null },
                onAddToBooking = { item ->
                    onNavigateToBooking(item.title)
                }
            )
        }
    }
}
