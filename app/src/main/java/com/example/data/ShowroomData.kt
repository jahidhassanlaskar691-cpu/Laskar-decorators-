package com.example.data

import com.example.model.CatalogItem
import com.example.model.EventSpace
import com.example.model.HotspotItem
import com.example.model.ServiceCategory

object ShowroomData {
    const val BUSINESS_NAME = "Laskar Tent Gift Mike House & Decorators"
    const val SHORT_NAME = "Laskar Decorators"
    const val PHONE_PRIMARY = "7399389788"
    const val PHONE_SECONDARY = "9707847234"
    const val LOCATION = "Mohanpur, Hailakandi, Assam - 788150"
    const val WHATSAPP_CATALOG_URL = "https://wa.me/c/919707847234"
    const val WHATSAPP_NUMBER = "919707847234"

    val eventSpaces = listOf(
        EventSpace(
            id = "wedding",
            name = "Royal Wedding Shamiana",
            tag = "3D Virtual Space",
            description = "Regal red & gold draped pavilion with elevated stage dais, crystal chandeliers, floral arches, and VIP seating.",
            previewImage = "img_6_1732114728754_c2314361.jpg"
        ),
        EventSpace(
            id = "mike_dj",
            name = "Grand Mike House & DJ Sound",
            tag = "Sound & Audio Setup",
            description = "High-powered concert line-array speakers, heavy bass cabinets, wireless UHF mikes, and moving-head truss lighting.",
            previewImage = "null-20260920-WA0000_13266a9d.jpg"
        ),
        EventSpace(
            id = "reception",
            name = "Fairy Garden Reception Lawn",
            tag = "Open Air & Dining",
            description = "Breathtaking starlight fairy drop canopy, botanical entrance gates, banquet seating, and buffet pandals.",
            previewImage = "20250308_222258_0762eb08.jpg"
        ),
        EventSpace(
            id = "gift_shop",
            name = "Laskar Gift House & Crockery",
            tag = "Gifts & Party Favors",
            description = "Wedding return gifts, school bags, designer umbrellas, luxury dinnerware, and novelty gift collections.",
            previewImage = "hypra-women-bag_e7c7de46.jpg"
        )
    )

    val hotspotList = listOf(
        HotspotItem(
            id = "mandap_arch",
            title = "Royal Floral Mandap & Stage",
            category = "Wedding Decor",
            price = "₹35,000 - ₹65,000",
            description = "Handcrafted grand wedding stage with royal red drapes, LED profile spotlights, fresh floral archways, and deluxe Maharaja couple sofa.",
            imageName = "img_6_1732114728754_c2314361.jpg",
            spaceId = "wedding",
            features = listOf(
                "Custom floral arch with seasonal fresh flowers",
                "Deluxe velvet couple sofa dais",
                "Warm profile spotlights & back illumination",
                "Includes complete setup and teardown"
            )
        ),
        HotspotItem(
            id = "shamiana_canopy",
            title = "Luxury Scalloped Tent & Drapes",
            category = "Tent Structure",
            price = "₹25,000 - ₹50,000",
            description = "Weather-resistant luxury shamiana with pleated satin ceiling fabric, golden scalloped borders, and heavy-duty steel pipe trusses.",
            imageName = "img_5_1732023677261_f4db4e91.jpg",
            spaceId = "wedding",
            features = listOf(
                "Up to 1,000 sq ft modular coverage",
                "Waterproof & wind-resistant canopy",
                "Dual-tone pleated ceiling drapes",
                "Decorative entrance side curtains"
            )
        ),
        HotspotItem(
            id = "chandelier_lights",
            title = "Crystal Chandeliers & Fairy Strings",
            category = "Lighting & Ambience",
            price = "₹12,000 - ₹25,000",
            description = "Warm ambient crystal chandelier centerpieces combined with cascading warm-white rice fairy lights spanning the entire canopy.",
            imageName = "img_9_1732114746782_1069b27c.jpg",
            spaceId = "wedding",
            features = listOf(
                "Multi-tiered glowing crystal chandeliers",
                "Warm-white rice LED ceiling grid",
                "Voltage stabilizer and safety distribution box",
                "Generator compatibility"
            )
        ),
        HotspotItem(
            id = "vip_seating",
            title = "VIP Velvet Sofa & Banquet Chairs",
            category = "Seating Setup",
            price = "₹15,000 - ₹30,000",
            description = "Royal Maharaja sofa set, 150+ covered banquet chairs with matching organza chair bows and round dining tables.",
            imageName = "IMG_20230301_185635_08ee2896.jpg",
            spaceId = "wedding",
            features = listOf(
                "150+ cushioned banquet chairs with satin slipcovers",
                "Royal Maharaja double couple sofa",
                "10 Round banquet tables with cloth draping",
                "Clean, pristine condition guaranteed"
            )
        ),
        HotspotItem(
            id = "line_array",
            title = "Dual Line-Array Tower Speakers",
            category = "Sound & Mike House",
            price = "₹18,000 - ₹40,000",
            description = "Concert-grade dual line-array acoustic tops with heavy bass scoop subwoofers for crystal-clear coverage up to 3,000+ guests.",
            imageName = "null-20260920-WA0000_13266a9d.jpg",
            spaceId = "mike_dj",
            features = listOf(
                "Twin line-array vertical acoustic towers",
                "Dual 18-inch heavy bass scoop bins",
                "Digital power amplifiers & crossovers",
                "Dedicated sound engineer on-site"
            )
        ),
        HotspotItem(
            id = "dj_console",
            title = "DJ Mixer, Console & Mike Setup",
            category = "Sound House",
            price = "₹8,000 - ₹18,000",
            description = "Pioneer DJ deck setup with high-gain UHF wireless cordless mikes, vocal processors, and backup generator connectivity.",
            imageName = "null-20260920-WA0011_9cb66ad0.jpg",
            spaceId = "mike_dj",
            features = listOf(
                "4 UHF cordless wireless handheld mikes",
                "Multi-channel audio mixing desk",
                "Stage monitor wedge speakers",
                "Echo & reverb vocal effects processors"
            )
        ),
        HotspotItem(
            id = "stage_lighting",
            title = "Moving Head Sharpy & RGB Truss",
            category = "Stage Lighting",
            price = "₹14,000 - ₹28,000",
            description = "High-power 10R beam moving heads, RGB LED par cans, laser beam effects, and heavy fog smoke machines.",
            imageName = "IMG_20230509_224932_11cd1bcc.jpg",
            spaceId = "mike_dj",
            features = listOf(
                "4x Beam Sharpy moving lights with gobo patterns",
                "12x High-power RGB LED Par Cans",
                "Heavy smoke fogger machine",
                "DMX computerized light programmer"
            )
        ),
        HotspotItem(
            id = "hanging_lights_canopy",
            title = "Starlight Fairy Drop Canopy",
            category = "Outdoor Lighting",
            price = "₹15,000 - ₹32,000",
            description = "Thousands of warm fairy micro-LEDs creating a magical glowing night ceiling over the banquet lawn.",
            imageName = "20250308_222258_0762eb08.jpg",
            spaceId = "reception",
            features = listOf(
                "Curtain drop fairy lights spanning lawn",
                "Warm golden color temperature (3000K)",
                "Outdoor insulated electrical cabling",
                "Spectacular photography background"
            )
        ),
        HotspotItem(
            id = "return_gifts",
            title = "Party & Wedding Return Favors",
            category = "Gift Center",
            price = "₹150 - ₹1,500 / piece",
            description = "Customized gift boxes, fashionable bags, decorative baskets, and celebratory souvenir gifts for wedding guests.",
            imageName = "hypra-women-bag_e7c7de46.jpg",
            spaceId = "gift_shop",
            features = listOf(
                "Bulk wedding discount packaging",
                "Custom ribbon & tag printing available",
                "Wide selection of bags & souvenirs",
                "Ready for immediate dispatch"
            )
        ),
        HotspotItem(
            id = "umbrellas_corner",
            title = "Designer Umbrellas & Kids Corner",
            category = "Novelty Gifts",
            price = "₹250 - ₹650 / piece",
            description = "Reverse C-handle umbrellas, 3-fold floral umbrellas, Spiderman school bags, and cute stationery sets.",
            imageName = "kids-reverse-umbrella-500x500_86c657ba.jpg",
            spaceId = "gift_shop",
            features = listOf(
                "Double-layer inverted reverse umbrellas",
                "Character backpacks (Spiderman, etc.)",
                "Sturdy windproof frames",
                "Wholesale & retail rates"
            )
        ),
        HotspotItem(
            id = "crockery_display",
            title = "Luxury Melamine & Kitchenware",
            category = "Diningware",
            price = "₹450 - ₹3,500 / set",
            description = "Heavy-duty melamine printed dinner plates, stainless spoons, serving bowls, and catering utensils for events.",
            imageName = "Kitchen_Ware_01f4b2b7.jpeg",
            spaceId = "gift_shop",
            features = listOf(
                "Shatter-proof premium melamine plates",
                "High-grade stainless steel spoons & serving sets",
                "Available for direct purchase or event hire",
                "Elegant traditional floral prints"
            )
        )
    )

    val catalogItems = listOf(
        CatalogItem(
            id = "cat_1",
            title = "Grand Wedding Stage & Mandap",
            category = ServiceCategory.STAGES,
            price = "₹35,000 - ₹65,000",
            description = "Royal carved pillars, floral archways, couple sofa, spotlight illumination, and carpet walkway.",
            imageName = "img_6_1732114728754_c2314361.jpg",
            tag = "Most Popular",
            inclusions = listOf("Stage structure", "Floral arch", "Couple sofa", "Spotlights")
        ),
        CatalogItem(
            id = "cat_2",
            title = "Luxury Weatherproof Shamiana",
            category = ServiceCategory.TENTS,
            price = "₹25,000 - ₹60,000",
            description = "Heavy duty steel pipe framework with waterproof roof and satin ceiling drapes in custom color themes.",
            imageName = "img_5_1732023677261_f4db4e91.jpg",
            tag = "Essential",
            inclusions = listOf("Pipes & canvas", "Ceiling drapes", "Side sidewalls", "Installation")
        ),
        CatalogItem(
            id = "cat_3",
            title = "High-Power DJ & Mike Sound Setup",
            category = ServiceCategory.SOUND_MIKE,
            price = "₹18,000 - ₹45,000",
            description = "Concert dual line array towers, twin heavy bass scoop subwoofers, cordless UHF mikes, and on-site operator.",
            imageName = "null-20260920-WA0000_13266a9d.jpg",
            tag = "Concert Grade",
            inclusions = listOf("Dual Line Arrays", "Subwoofers", "4x Wireless Mics", "Sound Operator")
        ),
        CatalogItem(
            id = "cat_4",
            title = "Grand Mike House Sound Console",
            category = ServiceCategory.SOUND_MIKE,
            price = "₹8,000 - ₹20,000",
            description = "Audio mixing consoles, studio stage monitoring, speech amplifiers, and PA horn speakers for community gatherings.",
            imageName = "null-20260920-WA0011_9cb66ad0.jpg",
            tag = "Crystal Sound",
            inclusions = listOf("Amplifier Rack", "Audio Mixer", "Horn Speakers", "Microphones")
        ),
        CatalogItem(
            id = "cat_5",
            title = "Night Chandelier & Fairy String Lights",
            category = ServiceCategory.LIGHTS,
            price = "₹12,000 - ₹28,000",
            description = "Multi-tiered crystal chandeliers combined with extensive rice fairy light grid across the entire venue.",
            imageName = "img_9_1732114746782_1069b27c.jpg",
            tag = "Magical Night",
            inclusions = listOf("Crystal Chandeliers", "Fairy Light Grid", "Cabling & Panels", "Operator")
        ),
        CatalogItem(
            id = "cat_6",
            title = "Festive Outdoor Reception Pandal",
            category = ServiceCategory.TENTS,
            price = "₹30,000 - ₹70,000",
            description = "Large-scale community pandal with decorative fabrics, entrance gate, and dining hall partition.",
            imageName = "20250408_160924_4f4b6b34.jpg",
            tag = "Grand Scale",
            inclusions = listOf("Heavy Structure", "Fabric Decoration", "Entrance Toran", "Dining Area")
        ),
        CatalogItem(
            id = "cat_7",
            title = "Entrance Archway & Red Carpet",
            category = ServiceCategory.STAGES,
            price = "₹8,000 - ₹18,000",
            description = "Welcome gate floral arch with warm LED pillars and red velvet aisle walkway.",
            imageName = "IMG_20230301_185635_08ee2896.jpg",
            tag = "Welcome",
            inclusions = listOf("Floral Gate", "LED Pillar Illumination", "Red Carpet Runner")
        ),
        CatalogItem(
            id = "cat_8",
            title = "Celebratory Moving Head Stage Lights",
            category = ServiceCategory.LIGHTS,
            price = "₹14,000 - ₹30,000",
            description = "Sharpy 10R moving beams, RGB par cans, and cold firework / smoke effects for bride-groom entry.",
            imageName = "IMG_20230509_224932_11cd1bcc.jpg",
            tag = "Party FX",
            inclusions = listOf("Moving Heads", "RGB LED Pars", "Smoke Fogger", "Light Tech")
        ),
        CatalogItem(
            id = "cat_9",
            title = "Kids Character School Bags & Favors",
            category = ServiceCategory.GIFTS,
            price = "₹350 - ₹950 / pc",
            description = "Durable premium embossed school bags, Spiderman graphics, comfortable padded shoulder straps.",
            imageName = "81KAzhSSLFL._AC_UY1100__c7c06744.jpg",
            tag = "Kids Favorite",
            inclusions = listOf("Water-resistant nylon", "Multiple compartments", "Wholesale pricing")
        ),
        CatalogItem(
            id = "cat_10",
            title = "Designer C-Handle Reverse Umbrellas",
            category = ServiceCategory.GIFTS,
            price = "₹350 - ₹650 / pc",
            description = "Double layer inverted hands-free C-handle umbrellas with inside-out folding technology.",
            imageName = "kids-reverse-umbrella-500x500_86c657ba.jpg",
            tag = "Gift Idea",
            inclusions = listOf("Double canopy", "C-shaped handle", "Wind-resistant frame")
        ),
        CatalogItem(
            id = "cat_11",
            title = "Women Luxury Handbag & Return Gifts",
            category = ServiceCategory.GIFTS,
            price = "₹450 - ₹1,400 / pc",
            description = "Chic trendy handbags with shoulder strap, ideal for wedding return gifts and party favors.",
            imageName = "hypra-women-bag_e7c7de46.jpg",
            tag = "Return Favors",
            inclusions = listOf("Premium faux leather", "Gift packaging box", "Assorted colors")
        ),
        CatalogItem(
            id = "cat_12",
            title = "Traditional Kashmiri Bridal Bangles",
            category = ServiceCategory.GIFTS,
            price = "₹250 - ₹850 / set",
            description = "Handcrafted festive bangles with intricate stone work and velvet lining for brides and guests.",
            imageName = "kashmiri_bangles__bangles__aesthetic__myvibecontest_a5dd6ae0.jpeg",
            tag = "Traditional",
            inclusions = listOf("Gift box set", "Assorted festive sizes", "Intricate stone inlay")
        ),
        CatalogItem(
            id = "cat_13",
            title = "Heavy Melamine Dinner Plate Sets",
            category = ServiceCategory.GIFTS,
            price = "₹600 - ₹2,400 / set",
            description = "Food-grade floral printed melamine plates, serving platters, and soup bowls for banquets & home.",
            imageName = "round-plastic-printed-plates_d6ce67cb.jpg",
            tag = "Catering Grade",
            inclusions = listOf("Heat resistant", "Scratch proof", "Available in sets of 6 or 12")
        ),
        CatalogItem(
            id = "cat_14",
            title = "Stainless Steel Cutlery & Spoons Pack",
            category = ServiceCategory.GIFTS,
            price = "₹180 - ₹750 / pack",
            description = "Mirror polished stainless steel dinner spoons, forks, and serving ladles.",
            imageName = "spoon-250x250_1606b23b.jpg",
            tag = "Event Utility",
            inclusions = listOf("Rust-free stainless steel", "Dishwasher safe", "Packs of 12 / 24")
        )
    )
}
