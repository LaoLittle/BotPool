package org.laolittle

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.events.BotOfflineEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.utils.info

object BotPool : KotlinPlugin(
    JvmPluginDescription(
        id = "org.laolittle.BotPool",
        name = "BotPool",
        version = "0.1.0",
    ) {
        author("LaoLittle")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }

        globalEventChannel().subscribeGroupMessages(priority = EventPriority.HIGHEST) {
            always {
                Pool.get(subject.id)?.let {
                    if (it !== subject) intercept()
                } ?: Pool.add(subject)
            }
        }

        globalEventChannel().subscribeAlways<BotOfflineEvent>(priority = EventPriority.HIGHEST) {
            Pool.remove(bot)
        }
    }
}