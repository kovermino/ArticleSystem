package com.joel.articlesystem.article.controller

import com.joel.articlesystem.article.domain.ArticleDTO
import com.joel.articlesystem.article.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController
@RequestMapping("/api/article")
class ArticleController(
        private val articleService: ArticleService
) {
    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: Int): ArticleDTO? {
        return articleService.findArticleById(id)
    }
}