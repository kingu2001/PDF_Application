package com.example.tedoexperiment.data

data class TestDocument(
    val id : Int,
    val fileName : String,
    val contentType : String,
)

data class TestDocumentResponse(val result : List<TestDocument>)