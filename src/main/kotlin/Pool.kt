package org.laolittle

import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Group
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.withLock

object Pool {
    private val rwLock = ReentrantReadWriteLock()

    private val enableGroups: MutableSet<Group> = hashSetOf()

    fun get(groupId: Long): Group? {
        rwLock.read {
            enableGroups.forEach {
                if (it.id == groupId) return it
            }
        }

        return null
    }

    fun add(group: Group): Boolean {
        val wl = rwLock.writeLock()
        return wl.withLock {
            enableGroups.add(group)
        }
    }

    fun remove(bot: Bot): Boolean {
        val wl = rwLock.writeLock()
        return wl.withLock {
            enableGroups.firstOrNull { it.bot === bot }?.let { enableGroups.remove(it) } ?: false
        }
    }
}