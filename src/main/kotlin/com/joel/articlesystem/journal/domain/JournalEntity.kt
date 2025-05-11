package com.joel.articlesystem.journal.domain

import jakarta.persistence.*

@Entity
@Table(name = "kd_journal")
data class JournalEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "jid")
        val id: Int,
        @Column(name = "journal_type")
        @Enumerated(EnumType.STRING)
        val type: JournalType,
        @Column(name = "journal")
        val title: String,
        @Column(name = "jo_publisher")
        val publisher: String,
        @Column(name = "issn")
        val issn: String
)
