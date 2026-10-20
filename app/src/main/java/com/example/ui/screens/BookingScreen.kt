package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ShowroomData
import com.example.model.BookingRequest
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WhatsAppGreen
import com.example.util.ContactHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    initialPreselectedItem: String = ""
) {
    val context = LocalContext.current

    var clientName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var eventType by remember { mutableStateOf("Wedding Ceremony") }
    var eventDate by remember { mutableStateOf("") }
    var guestCount by remember { mutableFloatStateOf(350f) }
    var venueLocation by remember { mutableStateOf("Mohanpur / Hailakandi, Assam") }
    var budgetRange by remember { mutableStateOf("₹30,000 - ₹60,000") }
    var specialNotes by remember {
        mutableStateOf(if (initialPreselectedItem.isNotBlank()) "Interested in setup: $initialPreselectedItem" else "")
    }

    val availableServices = listOf(
        "Tent & Shamiana Structure",
        "Mike House & DJ Sound Setup",
        "Stage & Mandap Floral Decor",
        "Chairs, Tables & Sofa Seating",
        "Luxury Lighting & Moving Heads",
        "Banquet Crockery & Catering Utensils",
        "Return Gifts & Favors Packaging"
    )

    var selectedServices by remember {
        mutableStateOf(
            if (initialPreselectedItem.isNotBlank()) {
                setOf("Tent & Shamiana Structure", "Stage & Mandap Floral Decor", "Mike House & DJ Sound Setup")
            } else {
                setOf("Tent & Shamiana Structure", "Mike House & DJ Sound Setup")
            }
        )
    }

    val eventTypes = listOf(
        "Wedding Ceremony",
        "Reception Party",
        "Birthday Bash",
        "DJ Night & Concert",
        "Religious Pandal",
        "VIP Tent Setup"
    )

    val budgetOptions = listOf(
        "₹15,000 - ₹30,000",
        "₹30,000 - ₹60,000",
        "₹60,000 - ₹1,20,000",
        "₹1,20,000+"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.EventAvailable,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Book Your Event Setup",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Get an instant quotation & check availability directly on WhatsApp.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        // Preselected Item Alert Banner
        if (initialPreselectedItem.isNotBlank()) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = GoldPrimary.copy(alpha = 0.15f),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = GoldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Selected from Showroom: $initialPreselectedItem",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary
                            )
                        )
                    }
                }
            }
        }

        // Section 1: Contact Details
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "1. Customer Information",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )

                    OutlinedTextField(
                        value = clientName,
                        onValueChange = { clientName = it },
                        label = { Text("Your Name") },
                        placeholder = { Text("e.g. Rahul Laskar") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("booking_name_input"),
                        shape = RoundedCornerShape(12.dp)
                    )

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("WhatsApp / Mobile Number") },
                        placeholder = { Text("e.g. 9876543210") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("booking_phone_input"),
                        shape = RoundedCornerShape(12.dp)
                    )

                    OutlinedTextField(
                        value = venueLocation,
                        onValueChange = { venueLocation = it },
                        label = { Text("Event Venue / Town") },
                        placeholder = { Text("Mohanpur / Hailakandi / Silchar") },
                        leadingIcon = { Icon(Icons.Default.Place, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("booking_location_input"),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }

        // Section 2: Event Specifics
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "2. Event Details",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )

                    // Event Type Chips
                    Text(
                        text = "Select Event Occasion:",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(eventTypes) { type ->
                            val isSelected = eventType == type
                            FilterChip(
                                selected = isSelected,
                                onClick = { eventType = type },
                                label = { Text(type, style = MaterialTheme.typography.labelSmall) },
                                shape = RoundedCornerShape(10.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = GoldPrimary,
                                    selectedLabelColor = Color(0xFF120E18)
                                )
                            )
                        }
                    }

                    // Event Date
                    OutlinedTextField(
                        value = eventDate,
                        onValueChange = { eventDate = it },
                        label = { Text("Tentative Event Date(s)") },
                        placeholder = { Text("e.g. 15th December 2026") },
                        leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("booking_date_input"),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // Guest Count Slider
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Expected Guests:",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                            Text(
                                text = "${guestCount.toInt()} People",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GoldPrimary
                                )
                            )
                        }
                        Slider(
                            value = guestCount,
                            onValueChange = { guestCount = it },
                            valueRange = 50f..2500f,
                            steps = 48,
                            colors = SliderDefaults.colors(
                                thumbColor = GoldPrimary,
                                activeTrackColor = GoldPrimary
                            ),
                            modifier = Modifier.testTag("guest_count_slider")
                        )
                    }
                }
            }
        }

        // Section 3: Required Services
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "3. Required Services & Items",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    availableServices.forEach { service ->
                        val isChecked = selectedServices.contains(service)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    selectedServices = if (isChecked) {
                                        selectedServices - service
                                    } else {
                                        selectedServices + service
                                    }
                                }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    selectedServices = if (checked) {
                                        selectedServices + service
                                    } else {
                                        selectedServices - service
                                    }
                                },
                                colors = CheckboxDefaults.colors(checkedColor = GoldPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = service,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (isChecked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }
        }

        // Section 4: Budget & Special Notes
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "4. Estimated Budget & Notes",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(budgetOptions) { budget ->
                            val isSelected = budgetRange == budget
                            FilterChip(
                                selected = isSelected,
                                onClick = { budgetRange = budget },
                                label = { Text(budget, style = MaterialTheme.typography.labelSmall) },
                                shape = RoundedCornerShape(10.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = GoldPrimary,
                                    selectedLabelColor = Color(0xFF120E18)
                                )
                            )
                        }
                    }

                    OutlinedTextField(
                        value = specialNotes,
                        onValueChange = { specialNotes = it },
                        label = { Text("Special Requests / Item Specifics") },
                        placeholder = { Text("Specify colors, theme preferences, specific gifts, speaker requirements...") },
                        maxLines = 4,
                        modifier = Modifier.fillMaxWidth().testTag("booking_notes_input"),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }

        // Section 5: Submit Actions
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        val booking = BookingRequest(
                            clientName = clientName,
                            phoneNumber = phoneNumber,
                            eventType = eventType,
                            eventDate = eventDate,
                            guestCount = guestCount.toInt(),
                            venueLocation = venueLocation,
                            selectedServices = selectedServices,
                            budgetRange = budgetRange,
                            specialNotes = specialNotes
                        )
                        val msg = ContactHelper.createBookingWhatsAppMessage(booking)
                        ContactHelper.openWhatsAppMessage(context, msg)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("submit_whatsapp_booking_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Default.Chat, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Send Inquiry via WhatsApp",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { ContactHelper.dialPhone(context, ShowroomData.PHONE_PRIMARY) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("call_primary_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Call 7399389788", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    OutlinedButton(
                        onClick = { ContactHelper.dialPhone(context, ShowroomData.PHONE_SECONDARY) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("call_secondary_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Call 9707847234", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}
