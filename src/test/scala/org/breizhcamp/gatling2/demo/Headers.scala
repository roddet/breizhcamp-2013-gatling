package org.breizhcamp.gatling2.demo

object Headers {
  val headers_1 = Map(
    """Accept""" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8""",
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache""")

  val headers_2 = Map(
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache""",
    """X-Requested-With""" -> """XMLHttpRequest""")

  val headers_6 = Map(
    """Accept""" -> """*/*""",
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache""")
}