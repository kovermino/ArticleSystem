package com.joel.articlesystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArticleSystemApplication

fun main(args: Array<String>) {
    runApplication<ArticleSystemApplication>(*args)
}
