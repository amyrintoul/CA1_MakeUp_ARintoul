package com.example.ca1_makeup_arintoul.data

import java.util.*

class SampleDataProvider {
    companion object {
        private val sampleText1 = "KASH BEAUTY\n" + "CRYSTAL NIGHTS LIP SET"
        private val sampleText2 = "ANASTASIA BEVERLY HILLS\n" + "PRIMROSE PALETTE"
        private val sampleText3 = """
         Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed congue nisi sed hendrerit accumsan. Nam tristique tincidunt odio. Sed scelerisque pellentesque dolor, ac auctor neque faucibus vel. Phasellus volutpat, dolor in luctus venenatis, nunc velit tempor justo, vitae egestas massa felis sit amet dolor. Morbi ex ligula, laoreet in mi quis, efficitur aliquam augue. Quisque sapien enim, tincidunt lacinia pulvinar in, molestie sed augue. Sed laoreet ligula eu metus sagittis tempus. Suspendisse luctus tellus id dui egestas, eu porta tellus posuere.

         Nulla mauris risus, facilisis at fermentum eu, pellentesque non erat. Donec in pharetra orci, aliquam pulvinar magna. Nunc nec arcu vel elit fringilla semper id at odio. Sed eget neque consequat, mollis massa id, mollis elit. Nunc facilisis nulla et ligula finibus, vel dignissim est porttitor. Duis ac mauris nulla. Donec at vulputate enim.
    
        """.trimIndent()



        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

       fun getProducts() = arrayListOf(
            ProductEntity( getDate(0), sampleText1, "description", 0, "brand", "brand"),
            ProductEntity( getDate(1), sampleText2, "description", 0, "brand", "brand"),
            ProductEntity( getDate(2), sampleText3, "description", 0, "brand", "brand"),

           )
    }
}