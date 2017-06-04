package com.vhra.notes.webservice

import com.vhra.notes.processor.Processor

interface Executor {
    public fun execute(url: String, processor: Processor)
}