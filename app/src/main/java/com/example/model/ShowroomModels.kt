package com.example.model

enum class ServiceCategory(val displayName: String, val iconName: String) {
    ALL("All Services", "Stars"),
    TENTS("Tents & Shamiana", "Tent"),
    SOUND_MIKE("Mike & Sound House", "VolumeUp"),
    STAGES("Stages & Pandals", "Festival"),
    GIFTS("Gifts & Crockery", "CardGiftcard"),
    LIGHTS("Lighting & FX", "Lightbulb")
}

data class HotspotItem(
    val id: String,
    val title: String,
    val category: String,
    val price: String,
    val description: String,
    val imageName: String,
    val spaceId: String = "wedding",
    val features: List<String> = emptyList()
)

data class CatalogItem(
    val id: String,
    val title: String,
    val category: ServiceCategory,
    val price: String,
    val description: String,
    val imageName: String,
    val tag: String = "Popular",
    val inclusions: List<String> = emptyList()
)

data class EventSpace(
    val id: String,
    val name: String,
    val tag: String,
    val description: String,
    val previewImage: String
)

data class BookingRequest(
    val clientName: String = "",
    val phoneNumber: String = "",
    val eventType: String = "Wedding Ceremony",
    val eventDate: String = "",
    val guestCount: Int = 300,
    val venueLocation: String = "Hailakandi / Mohanpur",
    val selectedServices: Set<String> = setOf("Tent & Shamiana", "Mike & Sound System"),
    val budgetRange: String = "₹30,000 - ₹60,000",
    val specialNotes: String = ""
)
