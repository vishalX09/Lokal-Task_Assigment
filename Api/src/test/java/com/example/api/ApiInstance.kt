package com.example.api

import org.junit.Assert
import org.junit.Test

interface ApiInstance {
   @Test
   fun `api tests only`(){
      Assert.assertNotEquals(4, 2 + 2)
   }
}