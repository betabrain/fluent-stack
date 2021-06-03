package ch.ergonomics

class FluentException(ex: Exception) : Exception("rethrow", ex)