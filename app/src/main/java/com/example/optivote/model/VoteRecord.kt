package com.example.optivote.model

class VoteRecord (val recordId: Int,
                  val vote: Vote,
                  val recordDectionnary: Map<User, String>)