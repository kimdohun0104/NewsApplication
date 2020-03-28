package com.dohun.remote.news

import java.util.*

interface TagExtractor {
    fun getTags(description: String): List<String>?
}

class TagExtractorImpl : TagExtractor {

    companion object {
        private const val TAG_COUNT = 3
    }

    override fun getTags(description: String): List<String>? {
        if (description.isBlank()) return null

        val frequency = hashMapOf<String, Int>()
        description.split(" ")
            .filter { it.length > 2 }
            .forEach { frequency[it] = (frequency[it] ?: 0) + 1 }
        val queue = PriorityQueue<String>(TAG_COUNT, Comparator<String> { o1, o2 ->
            when {
                frequency[o1]!! > frequency[o2]!! -> 1
                frequency[o1]!! < frequency[o2]!! -> -1
                else -> o2.compareTo(o1)
            }
        })
        frequency.keys.forEach {
            queue.add(it)
            if (queue.size > TAG_COUNT) {
                queue.poll()
            }
        }
        return queue.reversed()
    }

}