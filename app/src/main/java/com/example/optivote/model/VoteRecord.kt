package com.example.optivote.model

class VoteRecord (val recordId: Int,
                  val vote: Vote,
                  val recordDictionary: Map<User, String>)