package com.ame.lojinhatesteame.data.executor

interface TaskExecutor {
    fun executeOnDiskIO(runnable: Runnable)
    fun executeOnNetworkIO(runnable: Runnable)
    fun executeOnMainThread(runnable: Runnable)
}
