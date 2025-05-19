package com.joel.articlesystem.article.repository.rdb.entity

import com.joel.articlesystem.journal.domain.JournalEntity
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "kd_article")
data class ArticleEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "mk")
        val id: Int? = null,
        @Column(name = "content_id")
        val source: String?,
        val title: String?,
        @Column(name = "abstract")
        val abstracts: String?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "jid")
        val journalEntity: JournalEntity?,
        val aCode: String? = null,
        val doi: String? = null,
        @Column(name = "indexed_at")
        var indexedAt: ZonedDateTime? = null

//        @Column(name = "c_type")
//        val articleType: String? = null,
//
//        @Column(name = "title2")
//        var subTitle: String? = null,
//
//        val keywords: String? = null,
//
//        @Column(name = "keywords2")
//        var subKeywords: String? = null,
//
//        @Column(name = "abstract_title")
//        var abstractsTitle: String? = null,
//
//        @Column(name = "abstract")
//        var abstractsContent: String? = null,
//
//        @Column(name = "abstract2_title")
//        var subAbstractsTitle: String? = null,
//
//        @Column(name = "abstract2")
//        var subAbstractsContent: String? = null,
//
//        val jid: Int? = null,
//
//        val journal: String? = null,
//
//        val publisher: String? = null,
//
//        @Column(name = "p_year")
//        var publishYear: String? = null,
//
//        @Column(name = "yymm")
//        var yearAndMonth: String? = null,
//
//        val articleUrl: String? = null,
//
//        val fulltextUrl: String? = null,
//
//        @Column(name = "pubyear_id")
//        var publishYearId: Int? = null,
//
//        @Column(name = "pid")
//        var publisherId: Int? = null,
//
//        val issn: String? = null,
//
//        val volumeIssue: String? = null,
//
//        @Column(name = "regdate")
//        var regDate: LocalDate? = null,
//
//        val updateDate: LocalDate? = null,
//
//        val editor: String? = null,
//
//        @Column(name = "startpage")
//        var startPage: String? = null,
//
//        @Column(name = "endpage")
//        var endPage: String? = null,
//
//        @Column(name = "totalpage")
//        var totalPage: String? = null,
//
//        @Column(name = "a_format")
//        var originFormat: String? = null,
//
//        val pdf: String? = null,
//
//        val mrgMk: Int? = null,
//
//        @Column(name = "serv_yn")
//        var serviceYn: String? = null,
//
//        val oaYn: String? = null,
//
) {
}