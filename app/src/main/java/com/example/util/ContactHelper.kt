package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.data.ShowroomData
import com.example.model.BookingRequest
import com.example.model.CatalogItem
import com.example.model.HotspotItem
import java.net.URLEncoder

object ContactHelper {

    fun dialPhone(context: Context, phoneNumber: String = ShowroomData.PHONE_PRIMARY) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open dialer for $phoneNumber", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsAppMessage(context: Context, message: String) {
        try {
            val encodedMsg = URLEncoder.encode(message, "UTF-8")
            val url = "https://wa.me/${ShowroomData.WHATSAPP_NUMBER}?text=$encodedMsg"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp. Please check if installed.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openCatalogInWhatsApp(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ShowroomData.WHATSAPP_CATALOG_URL))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Opening catalog...", Toast.LENGTH_SHORT).show()
        }
    }

    fun openMapLocation(context: Context) {
        try {
            val geoUri = Uri.parse("geo:0,0?q=Mohanpur,+Hailakandi,+Assam+788150+(Laskar+Tent+Gift+Mike+House+and+Decorators)")
            val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            val webMap = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=Mohanpur,+Hailakandi,+Assam+788150"))
            context.startActivity(webMap)
        }
    }

    fun createBookingWhatsAppMessage(booking: BookingRequest): String {
        return buildString {
            appendLine("🌟 *EVENT BOOKING INQUIRY* 🌟")
            appendLine("To: *${ShowroomData.BUSINESS_NAME}*")
            appendLine("Location: Mohanpur, Hailakandi, Assam")
            appendLine("----------------------------------------")
            appendLine("👤 *Client Name:* ${booking.clientName.ifBlank { "Valued Customer" }}")
            appendLine("📞 *Contact Number:* ${booking.phoneNumber.ifBlank { "Not provided" }}")
            appendLine("🎉 *Event Type:* ${booking.eventType}")
            appendLine("📅 *Event Date:* ${booking.eventDate.ifBlank { "To be finalized" }}")
            appendLine("👥 *Guest Count:* ${booking.guestCount} guests")
            appendLine("📍 *Venue Location:* ${booking.venueLocation}")
            appendLine("💰 *Estimated Budget:* ${booking.budgetRange}")
            appendLine("🛠️ *Services Needed:*")
            booking.selectedServices.forEach { service ->
                appendLine("   • $service")
            }
            if (booking.specialNotes.isNotBlank()) {
                appendLine("📝 *Special Requests:* ${booking.specialNotes}")
            }
            appendLine("----------------------------------------")
            appendLine("Sent from Laskar Decorators 3D Showroom App. Please share quotation and availability!")
        }
    }

    fun createHotspotWhatsAppMessage(item: HotspotItem): String {
        return buildString {
            appendLine("✨ *INQUIRY FROM 3D SHOWROOM* ✨")
            appendLine("I am interested in this setup from your 3D Showroom:")
            appendLine("📌 *Item/Setup:* ${item.title}")
            appendLine("🏷️ *Category:* ${item.category}")
            appendLine("💵 *Price Range:* ${item.price}")
            appendLine("ℹ️ *Details:* ${item.description}")
            appendLine("----------------------------------------")
            appendLine("Please let me know if this is available for my upcoming event date!")
        }
    }

    fun createCatalogWhatsAppMessage(item: CatalogItem): String {
        return buildString {
            appendLine("🛍️ *CATALOG INQUIRY* 🛍️")
            appendLine("Hello Laskar Decorators, I want to book/order:")
            appendLine("📌 *${item.title}*")
            appendLine("🏷️ Category: ${item.category.displayName}")
            appendLine("💵 Price: ${item.price}")
            appendLine("ℹ️ Description: ${item.description}")
            appendLine("----------------------------------------")
            appendLine("Please share booking terms, dates, and order options!")
        }
    }
}

fun jsonFromHotspot(item: HotspotItem): String {
    val cleanDesc = item.description.replace("\"", "\\\"").replace("'", "\\'")
    val cleanTitle = item.title.replace("\"", "\\\"").replace("'", "\\'")
    val cleanCat = item.category.replace("\"", "\\\"").replace("'", "\\'")
    val cleanPrice = item.price.replace("\"", "\\\"").replace("'", "\\'")
    val cleanImage = item.imageName.replace("\"", "\\\"").replace("'", "\\'")
    return "{\"id\":\"${item.id}\",\"title\":\"$cleanTitle\",\"category\":\"$cleanCat\",\"price\":\"$cleanPrice\",\"desc\":\"$cleanDesc\",\"image\":\"$cleanImage\",\"pos\":{\"x\":0,\"y\":3,\"z\":0}}"
}

