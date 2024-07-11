package com.ahmedmq.tas.playground

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository

@SpringBootApplication
@EnableConfigurationProperties
class TasPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<TasPlaygroundApplication>(*args)
}

interface BookmarkRepository : JpaRepository<Bookmark, Long>

@Entity
@Table(name = "bookmarks")
data class Bookmark(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long, @NotNull val title: String, @NotNull val url: String)
