package com.ghostly.android.posts.models

sealed class Filter(val key: String, val filterName: String, val title: String) {
    data object All : Filter("all", "All", "All")
    data object Drafts : Filter("draft", "Drafts", "Draft")
    data object Published : Filter("published", "Published", "Published")
    data object Scheduled : Filter("scheduled", "Scheduled", "Scheduled")

    companion object {
        fun statusKeyToFilter(status: String): Filter {
            return when (status) {
                Drafts.key -> Drafts
                Published.key -> Published
                Scheduled.key -> Scheduled
                else -> All
            }
        }
    }
}