package com.ahmedmq.tas.playground

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/bookmarks")
class BookmarkController(val bookmarkRepository: BookmarkRepository) {
    private val log: Logger = LoggerFactory.getLogger(BookmarkController::class.java)

    @PostMapping()
    fun bookmark(@RequestBody bookmark: Bookmark) {
        val saved = bookmarkRepository.save(bookmark)
        log.debug("Saved Bookmark: {}", saved)
    }

    @GetMapping
    fun bookmarks(): MutableList<Bookmark> = bookmarkRepository.findAll()

}