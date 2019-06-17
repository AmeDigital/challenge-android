package marco.challenge_android.data

open class LojinhaService{

    fun getService(): LojinhaAPI {
        return LojinhaClient.getClient()
    }
}